package ${package}.rest;

import com.zed.aop.log.Log;
import ${package}.domain.${className};
import ${package}.service.${className}Service;
import ${package}.service.dto.${className}QueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
* ${className}Controller
*
* @author ${author}
* @date ${date}
*/
@Api(tags = "${className}管理")
@RestController
@RequestMapping("/api/${changeClassName}")
public class ${className}Controller {

@Autowired
private  ${className}Service ${changeClassName}Service;

@ApiOperation("分页查询${className}")
@GetMapping("/pageList")
@PreAuthorize("@el.check('${changeClassName}:list')")
public ResponseEntity searchPage(PageParam pageParam, ${className}QueryAO queryAO){
${className}DTO dto = AutoMapperUtil.toPOJO(queryAO, ${className}DTO.class);
return new ResponseEntity<>(${changeClassName}Service.searchPage(pageParam,dto),HttpStatus.OK);
}

@ApiOperation("查询${className}")
@GetMapping("/{${pkChangeColName}}")
@PreAuthorize("@el.check('${changeClassName}:detail')")
public ResponseEntity get${className}(@PathVariable ${pkColumnType} ${pkChangeColName}){
${className}VO vo = ${changeClassName}Service.get${className}ById(${pkChangeColName});
return new ResponseEntity<>(vo,HttpStatus.OK);
}


@Log("新增${className}")
@ApiOperation("新增${className}")
@PostMapping("/add")
@PreAuthorize("@el.check('${changeClassName}:add')")
public ResponseEntity add(@Validated @RequestBody ${className}AddAO addAO){
// 转换
${className}DTO dto = AutoMapperUtil.toPOJO(addAO, ${className}DTO.class);
${changeClassName}Verify.verifyRepeat(dto);
${changeClassName}Service.add${className}(dto);
return new ResponseEntity<>(HttpStatus.CREATED);
}

@Log("修改${className}")
@ApiOperation("修改${className}")
@PutMapping("/update")
@PreAuthorize("@el.check('${changeClassName}:edit')")
public ResponseEntity update(@Validated @RequestBody ${className}UpdateAO updateAO){
// 转换
${className}DTO dto = AutoMapperUtil.toPOJO(updateAO, ${className}DTO.class);
${changeClassName}Verify.verifyRepeat(dto);
${changeClassName}Service.update${className}(dto);
return new ResponseEntity(HttpStatus.NO_CONTENT);
}

@Log("删除${className}")
@ApiOperation("删除${className}")
@DeleteMapping("/{${pkChangeColName}}")
@PreAuthorize("@el.check('${changeClassName}:del')")
public ResponseEntity delete(@PathVariable ${pkColumnType} ${pkChangeColName}){
${changeClassName}Service.delete(${pkChangeColName});
return new ResponseEntity(HttpStatus.OK);
}

}