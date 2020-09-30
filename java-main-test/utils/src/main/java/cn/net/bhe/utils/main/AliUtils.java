package cn.net.bhe.utils.main;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class AliUtils {

    static final Logger log = LoggerFactory.getLogger(AliUtils.class);

    /**
     * 发送短信的配置信息
     */
    public static class SConfig {
        public final String accessKeyId; 
        public final String accessKeySecret; 
        public final String signName; 
        public final String templateCode; 
        private String templateParam; 
        
        /**
         * @param accessKeyId  		用户标识
         * @param accessKeySecret	验证用户的密钥
         * @param signName			短信签名
         * @param templateCode		模版CODE
         * @param templateParam		模板变量替换JSON串
         */
        public SConfig(String accessKeyId, String accessKeySecret, String signName, String templateCode, String templateParam) {
            this.accessKeyId = accessKeyId;
            this.accessKeySecret = accessKeySecret;
            this.signName = signName;
            this.templateCode = templateCode;
            this.templateParam = templateParam;
        }

        /**
         * 设置模板变量替换JSON串
         * @param templateParam
         * @return
         */
        public SConfig setTemplateParam(String templateParam) {
            this.templateParam = templateParam;
            return this;
        }
        
        /**
         * 获取模板变量替换JSON串
         * @return
         */
        public String getTemplateParam() {
        	return this.templateParam;
        }
    }

    @Test
    public void sendSms() {
        try {
            sendSms(new SConfig(
                    "LTAI4G43YyvF2GHSqJvY7V7C", 
                    "uXYfXV6wPaw53bQNYfPckjt3Cm8rCW", 
                    "ABC商城", 
                    "SMS_203196911",
                    "{\"code\": \"254365\"}"), 
                    "15186942525");
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 发送短信
     * @param sConfig	配置类
     * @param phone		目标手机号
     * @return
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static SendSmsResponse sendSms(SConfig sConfig, String phone) throws Exception {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", sConfig.accessKeyId, sConfig.accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(phone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(sConfig.signName);
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(sConfig.templateCode);
        // 可选:模板中的变量替换JSON串
        if (StringUtils.isNotEmpty(sConfig.getTemplateParam())) {
        	request.setTemplateParam(sConfig.getTemplateParam());
        }

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        // request.setOutId("yourOutId");

        // hint 此处可能会抛出异常,注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        
        log.debug(JsonUtils.string(sendSmsResponse));

        return sendSmsResponse;
    }
    
    /**
     * 查询短信的配置信息
     */
    public static class QConfig {
    	public final String accessKeyId; 
    	public final String accessKeySecret; 
    	public final String sendDate;
    	public final long pageSize;
    	public final long currentPage;
        private String bizId;
        
        /**
         * @param accessKeyId  		用户标识
         * @param accessKeySecret	验证用户的密钥
         * @param sendDate			短信发送日期格式yyyyMMdd，支持最近30天记录查询。
         * @param pageSize			页大小Max=50
         * @param currentPage		当前页码，从1开始
         */
        public QConfig(String accessKeyId, String accessKeySecret, String sendDate, long pageSize, long currentPage) {
        	this.accessKeyId = accessKeyId;
        	this.accessKeySecret = accessKeySecret;
        	this.sendDate = sendDate;
        	this.pageSize = pageSize;
        	this.currentPage = currentPage;
        }
        
        /**
         * 设置发送流水号
         * @param bizId
         * @return
         */
        public QConfig setBizId(String bizId) {
        	this.bizId = bizId;
        	return this;
        }
        
        /**
         * 获取发送流水号
         * @return
         */
        public String getBizId() {
        	return this.bizId;
        }
    }
    
    @Test
    public void querySendDetails() {
    	try {
    		querySendDetails(
        			new QConfig(
                            "LTAI4G43YyvF2GHSqJvY7V7C", 
                            "uXYfXV6wPaw53bQNYfPckjt3Cm8rCW", 
                            "20200924", 
                            10l,
                            1l),
        			"15186942525");
		} catch (Exception e) {
			log.error("", e);
		}
    }
    
    @SuppressWarnings("deprecation")
	public static QuerySendDetailsResponse querySendDetails(QConfig qConfig, String phone) throws Exception {
		// 设置超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 云通信产品-短信API服务产品名称（短信产品名固定，无需修改）
		final String product = "Dysmsapi";
		// 云通信产品-短信API服务产品域名（接口地址固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";

		// 初始化ascClient
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", qConfig.accessKeyId, qConfig.accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象
		QuerySendDetailsRequest request = new QuerySendDetailsRequest();
		// 必填-号码
		request.setPhoneNumber(phone);
		// 可选-调用发送短信接口时返回的BizId
		if (StringUtils.isNotEmpty(qConfig.getBizId())) {
			request.setBizId(qConfig.getBizId());
		}
		// 必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
		request.setSendDate(qConfig.sendDate);
		// 必填-页大小
		request.setPageSize(qConfig.pageSize);
		// 必填-当前页码从1开始计数
		request.setCurrentPage(qConfig.currentPage);

		// hint 此处可能会抛出异常，注意catch
		QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

		log.debug(JsonUtils.string(querySendDetailsResponse));
		
		return querySendDetailsResponse;
    }

}
