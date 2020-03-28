package com.dfh.csma.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dfh.csma.system.common.Result;
import com.dfh.csma.system.core.JeecgController;
import com.dfh.csma.system.entity.Cityreport;
import com.dfh.csma.system.entity.SysDepart;
import com.dfh.csma.system.mapper.SysDepartMapper;
import com.dfh.csma.system.model.FileModel;
import com.dfh.csma.system.query.QueryGenerator;
import com.dfh.csma.system.service.ICityreportService;
import com.dfh.csma.system.service.ISysBaseAPI;
import com.dfh.csma.system.service.UploadService;
import com.dfh.csma.system.shiro.vo.DefContants;
import com.dfh.csma.system.util.JwtUtil;
import com.dfh.csma.system.vo.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * @Description: 城市归集上报
 * @Author: jeecg-boot
 * @Date:   2020-03-27
 * @Version: V1.0
 */
@Api(tags="城市归集上报")
@RestController
@RequestMapping("/cityreport/cityreport")
@Slf4j
public class CityreportController extends JeecgController<Cityreport, ICityreportService> {
	@Autowired
	private ICityreportService cityreportService;

	@Autowired
	private UploadService uploadService;

	@Autowired
	private ISysBaseAPI sysBaseAPI;

	@Autowired
	private SysDepartMapper sysDepartMapper;

	@Autowired
	private HttpServletRequest request;

	//文件id
	private static String id;

	/**
	 * 分页列表查询
	 *
	 * @param cityreport
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@ApiOperation(value="城市归集上报-分页列表查询", notes="城市归集上报-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Cityreport cityreport,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Cityreport> queryWrapper = QueryGenerator.initQueryWrapper(cityreport, req.getParameterMap());
		Page<Cityreport> page = new Page<Cityreport>(pageNo, pageSize);
		IPage<Cityreport> pageList = cityreportService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 *   添加
	 *
	 * @param cityreport
	 * @return
	 */
	@ApiOperation(value="城市归集上报-添加", notes="城市归集上报-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Cityreport cityreport) {
		SysDepart sysDepart = sysDepartMapper.findByOrgCode(getSysUser().getOrgCode());
		cityreport.setCreateTime(new Date());
		cityreport.setReportstatus(1);//默认为1上报状态
		cityreport.setFileid(id);
		cityreport.setUploaduser(getSysUser().getRealname());
		cityreport.setTunitid(sysDepart.getId());
		cityreport.setUnitname(sysDepart.getDepartName());
		//TODO 所属细则
//		cityreport.setRuledi();


		cityreportService.save(cityreport);
		return Result.ok("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param cityreport
	 * @return
	 */
	@ApiOperation(value="城市归集上报-编辑", notes="城市归集上报-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Cityreport cityreport) {
		cityreportService.updateById(cityreport);
		return Result.ok("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="城市归集上报-通过id删除", notes="城市归集上报-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		cityreportService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@ApiOperation(value="城市归集上报-批量删除", notes="城市归集上报-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.cityreportService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@ApiOperation(value="城市归集上报-通过id查询", notes="城市归集上报-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Cityreport cityreport = cityreportService.getById(id);
		if(cityreport==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(cityreport);
	}



	/**
	 * excel导入
	 *
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public Result<?> importFile(@RequestParam(name="file") MultipartFile file) {
		Result<FileModel> result = new Result<>();
		try {
			FileModel fileModel = upload(file);
			uploadService.save(fileModel);
			result.setResult(fileModel);
			result.setMessage("上传成功！");
		} catch (Exception e) {
			log.error(e.getMessage());
			result.error500("操作失败");
		}
		return result;
	}

	/**
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public FileModel upload(MultipartFile file) throws Exception {
		FileModel fileModel = new FileModel();
		//获取MD5值
		byte[] bytes = file.getBytes();
		MessageDigest instance = MessageDigest.getInstance("MD5");
		byte[] digest = instance.digest(bytes);
		String md5Hex = new BigInteger(1, digest).toString(16);

		//得到文件的文件名
		String filename = file.getOriginalFilename();
		String before = StringUtils.substringBeforeLast(filename, ".");
		String last = StringUtils.substringAfterLast(filename, ".");
		filename = before + "-" + md5Hex + "." + last;
		//得到当前时间
		LocalDate date = LocalDate.now();
		//根据时间创建目录
		File targetpath = new File("E:\\A-Project-DFH\\csma\\src\\main\\java\\com\\dfh\\"+date);
		if(!targetpath.exists()) {
			//判断，如果目录不存在就创建目录
			targetpath.mkdirs();
		}
		//储存文件到该目录
		file.transferTo(new File(targetpath, filename));


		//设置fileModel对象
		String Id = UUID.randomUUID().toString().replace("-", "");
		id= Id;
		fileModel.setId(Id);
		fileModel.setUploaduser(getSysUser().getRealname());
		fileModel.setFilename(filename);
		fileModel.setUploadtime(new Date());
		fileModel.setFilepath(targetpath.getPath());
		//获取文件大小
		fileModel.setFilesize(file.getSize());

		fileModel.setMd5(md5Hex);
		return fileModel;
	}


	public LoginUser getSysUser() {

		String token = request.getHeader(DefContants.X_ACCESS_TOKEN);
		String username = JwtUtil.getUsername(token);
		LoginUser loginUser = sysBaseAPI.getUserByName(username);
		return loginUser;
	}

}
