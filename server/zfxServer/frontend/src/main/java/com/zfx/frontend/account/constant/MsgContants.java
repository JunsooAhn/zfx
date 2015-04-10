package com.moneylocker.frontend.account.constant;

public class MsgContants {

	/* 更新基本信息 */

	public static final String NICK_NAME_EMPTY = "昵称不能为空，谢谢！";

	public static final String NICK_NAME_TOO_LONG = "昵称不能超过8个字符，谢谢！";

	public static final String NICK_NAME_EXIST = "你来晚了，这个昵称已经被人用了，换一个试试吧";

	public static final String NICK_NAME_UPDATE_WITH_CREDIT = "更新昵称成功，+";

	public static final String NICK_NAME_UPDATE_SUCCESS = "恭喜，昵称修改成功了";

	public static final String NICK_NAME_UPDATE_FAILD = "昵称修改失败，请稍后再试！";

	public static final String GENDER_INVALID = "请输入正确的性别参数，谢谢！";

	public static final String GENDER_UPDATE_WITH_CREDIT = "更新性别成功，+";

	public static final String GENDER_UPDATE_SUCCESS = "更新性别成功！";

	public static final String GENDER_UPDATE_FAILD = "性别修改失败，请稍后再试！";
	
	public static final String VIEW_WEBSITE_UPDATE_WITH_CREDIT = "官网访问成功，+";

	public static final String VIEW_WEBSITE_UPDATE_SUCCESS = "官网访问成功！";

	public static final String VIEW_WEBSITE_UPDATE_FAILD = "官网访问失败，请稍后再试！";
	
	public static final String VIEW_WEBSITE_FAILD = "官网访问状态不正确，请稍后再试！";

	public static final String BIRTHDAY_EMPTY = "生日不能为空，谢谢！";

	public static final String BIRTHDAY_UPDATE_WITH_CREDIT = "更新生日成功，+";

	public static final String BIRTHDAY_UPDATE_SUCCESS = "更新生日成功！";

	public static final String BIRTHDAY_UPDATE_FAILD = "生日修改失败，请稍后再试！";

	public static final String BIRTHDAY_YEAR_FAILD = "请输入正确的生日年份！";

	public static final String BIRTHDAY_MONTH_FAILD = "请输入正确的生日月份！";

	public static final String OLD_PASSWORD_EMPTY = "旧密码不能为空，谢谢！";

	public static final String NEW_PASSWORD_EMPTY = "新密码不能为空，谢谢！";

	/* 更新密码 */

	public static final String PASSWORD_EMPTY = "密码不能为空，谢谢！";

	public static final String OLD_PASSWORD_WRNONG = "请输入正确的旧密码!";

	public static final String UPDATE_PASSWORD_ERROR = "密码修改失败，请稍后再试！";

	public static final String UPDATE_PASSWORD_SUCCESS = "设置密码成功！";

	/* 检查用户密码是否正确，以及手机号码是否存在 */

	public static final String CHECK_PASSWORD_SUCCESS = "密码正确！";

	public static final String CHECK_PASSWORD_ERROR = "密码错误！";

	public static final String CHECK_PHONE_EXIST = "该手机号已经存在，不可以使用该手机号码！";

	public static final String CHECK_PHONE_NOT_EXIST = "该手机号不存在，可以使用该手机号码！";

	/* 登录和登出 */

	public static final String LOGOUT_SUCCESS = "登出成功！";

	public static final String LOGOUT_FAILED = "登出失败！";

	public static final String LOGIN_PHONE_INVALID = "请输入合格的手机号码";

	public static final String LOGIN_PASSWORD_EMPTY = "登录密码不能未空，请输入登录密码";

	public static final String LOGIN_IMEI_INVALID = "IMEI不合格，请稍后再试";
	
	public static final String LOGIN_SUCCESS = "登录成功";

	public static final String LOGIN_PASSWORD_INCORRECT = "手机号码或者密码错误";

	public static final String LOGIN_ACCOUNT_FREEZE = "对不起，账户被冻结了，快去联系客服吧。电话：4006082333";

	public static final String LOGIN_ACCOUNT_CHANGE_FREQUENTLY = "您的本台手机上24小时内已切换超过3次（含3次）app账号啦！系统将自动将此次登录的账号锁定24小时，24小时后自动解除，其他账号可正常登录使用，具体小黑屋规则请点击查看规则";

	public static final String LOGIN_IEMI_ALREADY_EXISTS = "该IMEI对应多个账号，请使用手机号码登录";

	public static final String LOGIN_REGISTER_SUCCESS = "该IMEI没有对应账号，已经自动为您创建了新账号";

	/* 注册新用户 */

	public static final String REGISTER_SUCCESS = "注册成功！";

	public static final String REGISTER_FAILD = "注册失败！";

	public static final String REGISTER_DUPLICATED = "该手机号码已经注册过了，每个手机号只能注册一个账号！";
	
	public static final String REGISTER_IMEI_INVALID = "IMEI不合格，请稍后再试！";
	
	public static final String REGISTER_ANDROID_ID_INVALID = "Android ID 不正确";
	
	public static final String REGISTER_MAC_ADDRESS_INVALID = "Mac Address 不正确";
	
	public static final String REGISTER_PHONE_INVALID = "您输入的手机号码不合格，请输入正确的手机号码！";
	
	public static final String REGISTER_PASSWORD_EMPTY = "密码不能为空，谢谢！";
	
	public static final String REGISTER_VERIFY_CODE_EMPTY = "验证码不能为空，谢谢！";

	public static final String REGISTER_VERIFY_ERROR = "手机验证码错误，请填写正确的验证码，或重新获取！";

	/* 找回密码 */

	public static final String RETRIEVE_PASSWORD_VERIFY_ERROR = "手机验证码错误，请填写正确的验证码，或重新获取！";

	public static final String RETRIEVE_PASSWORD_SUCCESS = "找回密码成功，已经设定为新密码！";

	public static final String RETRIEVE_PASSWORD_FAILED = "找回密码失败！";

	public static final String RETRIEVE_PASSWORD_USER_NOT_EXIST = "该手机号码不存在对应的用户！";
	
	public static final String RETRIEVE_PASSWORD_EMPTY = "密码不能为空！";
	
	public static final String RETRIEVE_IMEI_INVALID = "IMEI不合格，请稍后再试！";
	
	public static final String RETRIEVE_PHONE_INVALID = "您输入的手机号码不合格，请输入正确的手机号码！";
	
	public static final String RETRIEVE_VERIFY_CODE_EMPTY = "验证码不能为空，谢谢！";

	/* 填写推荐人 */

	public static final String RECOMMEND_SUCCESS = "设置成功！";

	public static final String RECOMMEND_CAN_NOT_FILL_SELF = "请输入正确的推荐人，不能填写自己为推荐人，谢谢！";

	public static final String RECOMMEND_USER_NOT_EXIST = "你还是不是惠锁屏的用户，请先注册，谢谢！";

	public static final String RECOMMEND_ORIGIN_ALREAY_FILLED = "对不起，推荐人不可重复填写!";

	public static final String RECOMMEND_REFERENCE_CODE_NOT_EXIST = "该邀请码不存在。请填写正确的邀请码或填写默认邀请码123456789";

	public static final String RECOMMEND_ERROR = "邀请码填写失败，请稍后再试！";

	public static final String RECOMMEND_REFERENCE_CODE_EMPTY = "用户昵称、唯一推荐码或绑定的手机号不能为空!";
	
	/* 绑定手机号 */

	public static final String BIND_PHONE_SUCCESS = "绑定手机号码成功！";

	public static final String BIND_PHONE_ERROR = "绑定手机号码失败，请稍后再试！";

	public static final String BIND_PHONE_VERIFY_ERROR = "手机验证码错误，请填写正确的验证码，或重新获取！";

	public static final String BIND_PHONE_SUCCESS_WITH_CREDIT = "绑定手机号码成功，+";
	
	/* 重新绑定手机号 */

	public static final String REBIND_PHONE_SUCCESS = "重新绑定手机号码成功！";

	public static final String REBIND_PHONE_ERROR = "重新绑定手机号码失败，请稍后再试！";

	public static final String REBIND_TOO_FREQUENTLY = "在%d天内，只可以绑定一个电话号码";

	public static final String REBIND_PHONE_VERIFY_ERROR = "手机验证码错误，请填写正确的验证码，或重新获取！";

	public static final String REBIND_PHONE_OLD_PHONE_WRONG = "请输入正确的原手机号码！";

	public static final String REBIND_PHONE_PASSWORD_WRONG = "请输入正确的账户密码！";
}
