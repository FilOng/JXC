package com.BS.controller.admin;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.BS.entity.GoodsType;
import com.BS.entity.Log;
import com.BS.service.GoodsTypeService;
import com.BS.service.LogService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 后台管理角色Controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/goodsType")
public class GoodsTypeAdminController {
	
	@Resource
	private GoodsTypeService goodsTypeService;
	
	@Resource
	private LogService logService;

	
	/**
	 * 根据父节点获取所有复选框权限菜单
	 * @param parentId
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loadTreeInfo")
	@RequiresPermissions(value="商品管理")
	public String loadTreeInfo(Integer parentId)throws Exception{
		logService.save(new Log(Log.SEARCH_ACTION,"查询所有商品类别信息"));
		return getAllByParentId(-1).toString();
	}
	
	/**
	 * 根据父节点id和权限菜单id集合获取所有复选框菜单集合
	 * @param parentId
	 * @param menuIdList
	 * @return
	 */
	public JsonArray getAllByParentId(Integer parentId){
		JsonArray jsonArray=this.getByParentId(parentId);
		for(int i=0;i<jsonArray.size();i++){
			JsonObject jsonObject=(JsonObject) jsonArray.get(i);
			if("open".equals(jsonObject.get("state").getAsString())){
				continue;
			}else{
				jsonObject.add("children", getAllByParentId(jsonObject.get("id").getAsInt()));
			}
		}
		return jsonArray;
	}
	/**
	 * 根据父节点查询所有子节点
	 * @param parentId
	 * @param goodsTypeList
	 * @return
	 */
	public JsonArray getByParentId(Integer parentId){
		List<GoodsType> goodsTypeList=goodsTypeService.findByParentId(parentId);
		JsonArray jsonArray=new JsonArray();
		for(GoodsType goodsType : goodsTypeList){
			JsonObject jsonObject=new JsonObject();
			jsonObject.addProperty("id", goodsType.getId());//节点id
			jsonObject.addProperty("text", goodsType.getName());//节点名称
			if( goodsType.getState()==1){
				jsonObject.addProperty("state", "closed");//根节点
			}else{
				jsonObject.addProperty("state", "open");//叶子节点
			}
			jsonObject.addProperty("iconCls",  goodsType.getIcon());//节点图标
			JsonObject attributeObject=new JsonObject();//扩展属性
			attributeObject.addProperty("state", goodsType.getState());//节点状态
			jsonObject.add("attributes",attributeObject);
			jsonArray.add(jsonObject);
		}
			return jsonArray;
	}
	
	}

