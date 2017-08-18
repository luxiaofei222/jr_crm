package com.jingren.jing.weixin.bean.notify;
/**
* @Title: NotifyResult.java 
* @Package com.jingren.jing.weixin.bean.notify 
* @Description: TODO 微信异步通知结果
* @author 鲁晓飞 MR.Lu   
* @date 2017年4月18日 上午9:16:55 
* @version 网校+CRM系统 V1.0
 */
public class NotifyResult {

	private String return_code;//SUCCESS/FAIL 返回状态码
	private String return_msg;//返回信息，如非空，为错误原因 签名失败 参数格式校验错误
	private String appid;//微信分配的公众账号ID（企业号corpid即为此appId）
	private String mch_id;//微信支付分配的商户号
	private String device_info;//微信支付分配的终端设备号
	private String nonce_str;//随机字符串，不长于32位
	private String sign;//签名
	private String sign_type;
	private String result_code;//业务结果 SUCCESS/FAIL
	private String err_code;//SYSTEMERROR 错误返回的信息描述 
	private String err_code_des;//系统错误 错误返回的信息描述   
	private String openid;//用户在商户appid下的唯一标识
	private String is_subscribe;//用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
	private String trade_type;//交易类型 JSAPI、NATIVE、APP
	private String bank_type;//付款银行 银行类型，采用字符串类型的银行标识
	private Integer total_fee;//订单金额
	private Integer settlement_total_fee;//应结订单金额
	private String fee_type;//货币种类
	private Integer cash_fee;//现金支付金额
	private String cash_fee_type;//货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY
	private Integer coupon_fee;//代金券金额<=订单金额，订单金额-代金券金额=现金支付金额
	private Integer coupon_count;//代金券使用数量
	private String coupon_type_0;
	private String coupon_id_0;//代金券ID
	private Integer coupon_fee_0;//单个代金券支付金额
	private String transaction_id;//微信支付订单号
	private String out_trade_no;//商户订单号
	private String attach;//商家数据包
	private String time_end;//支付完成时间
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public Integer getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public void setSettlement_total_fee(Integer settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public Integer getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public Integer getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(Integer coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	public Integer getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
	}
	public String getCoupon_type_0() {
		return coupon_type_0;
	}
	public void setCoupon_type_0(String coupon_type_0) {
		this.coupon_type_0 = coupon_type_0;
	}
	public String getCoupon_id_0() {
		return coupon_id_0;
	}
	public void setCoupon_id_0(String coupon_id_0) {
		this.coupon_id_0 = coupon_id_0;
	}
	public Integer getCoupon_fee_0() {
		return coupon_fee_0;
	}
	public void setCoupon_fee_0(Integer coupon_fee_0) {
		this.coupon_fee_0 = coupon_fee_0;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	
}
