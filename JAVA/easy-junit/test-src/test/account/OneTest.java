package test.account;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.tools.tools;


public class OneTest {

	String strName = "";
	String strHead = "";
	String strUrl = "";
	String strBody = "";
	int nOvertime = 0;
	String strExpect = "";
	
	String strResult = "";
//	String strBaseurl = "http://192.168.80.7:84";
	String strBaseurl = "http://192.168.80.7:84";
	String strFilename = "./logs/log.txt";

	tools Ctools = new tools();

	@Test
	public void test_configversion() {
		strName = "（一）接口版本信息";
		strUrl = strBaseurl+"/route/configversion?format=json";
		strBody = "app_id=1";
		nOvertime = 5000;
		strExpect = "\"ver\":\"v";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}

	@Test
	public void test_citys() {
		strName = "（二）查询城市列表";
		strUrl = strBaseurl+"/route/citys?format=json";
		strBody = "app_id=1";
		nOvertime = 5000;
		strExpect = "[{";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}

	@Test
	public void test_hotkeys() {
		strName = "（三）热点道路及商圈列表信息";
		strUrl = strBaseurl+"/route/hotkeys?format=json";
		strBody = "app_id=1&ver=v13.1.00&city_id=210100";
		nOvertime = 5000;
		strExpect = "[{";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}

	@Test
	public void test_boards() {
		strName = "（四）获取路况支持所有看板信息";
		strUrl = strBaseurl+"/route/boards?format=json";
		strBody = "app_id=1";
		nOvertime = 5000;
		strExpect = "[{";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}

	@Test
	public void test_roadcondition() {
		strName = "（五）查指定城市地点附近路况";
		strUrl = strBaseurl+"/route/roadcondition?format=json";
		strBody = "city_id=210100&keywords=sanhaojie&app_id=1&radius=2000";
		nOvertime = 5000;
		strExpect = "[{";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}

	@Test
	public void test_hotroadcondition() {
		strName = "（六）查指定城市热点道路及商圈路况";
		strUrl = strBaseurl+"/route/hotroadcondition?format=json";
		strBody = "city_id=210100&hot_id=2101023&place=万象城&app_id=1";
		nOvertime = 5000;
		strExpect = "[{";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}

	@Test
	public void test_circlearea() {
		strName = "（七）路况信息圆形接口";
		strUrl = strBaseurl+"/route/circlearea?format=json";
		strBody = "lon=123.379169&lat=41.790545&radius=2000&app_id=1&city_id=210100&type=0";
		nOvertime = 5000;
		strExpect = "[{";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}

	@Test
	public void test_boardpic() {
		strName = "（八）获取路况看板图片";
		strUrl = strBaseurl+"/route/boardpic?format=json";
		strBody = "app_id=1&board_id=2102007";
		nOvertime = 5000;
		strExpect = "data";
		
		sendmsg(strName,strHead,strUrl,strBody,nOvertime,strExpect);
	}
	
	
	public void sendmsg(String strName,String strHead,String strUrl,String strBody,int nOvertime,String strExpect) {
		long startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		// 执行一次请求
		strResult = Ctools.sendMsg(strHead,strUrl,strBody);
		long endMili=System.currentTimeMillis();
		long totleMili = endMili-startMili;
		Ctools.logPrint(strFilename,"服务：<<<"+strName+">>>总耗时为："+totleMili+"毫秒");
		Ctools.logPrint(strFilename,strResult);
		if(strResult.indexOf(strExpect) == -1){
			fail("失败：<<<"+strName+">>>:"+strResult);
		}
		if(totleMili > nOvertime){
			fail("超时：<<<"+strName+">>>:"+totleMili+"毫秒("+nOvertime+")");
		}
	}
}