package com.example.base.enums;

public enum VerifyCodeEnum {

  // 1000 注册验证码,1001 注册图片验证码
  registerPic(1001, "注册图片验证码", "VCRP"),
  loginPic(1002, "登录图片验证码", "CODE"),
  loginToken(1003, "登录令牌", "TOKEN");

  private Integer codeType;
  private String codeName;
  private String codeKey;

  VerifyCodeEnum(Integer codeType, String codeName, String codeKey) {
    this.codeType = codeType;
    this.codeName = codeName;
    this.codeKey = codeKey;
  }

  public static String getCodeKey(Integer codeType){

    for (VerifyCodeEnum value : values()) {
      if (value.codeType.equals(codeType)){
        return value.codeKey;
      }
    }
    throw new RuntimeException("不支持的业务类型");

//    if (codeType == 1000) {
//      return VerifyCodeEnum.register.codeKey;
//    } else if (codeType == 1001) {
//      return VerifyCodeEnum.registerPic.codeKey;
//    } else if (codeType == 2000) {
//      return VerifyCodeEnum.find.codeKey;
//    } else if (codeType == 2001) {
//      return VerifyCodeEnum.findPic.codeKey;
//    } else if (codeType == 3000) {
//      return VerifyCodeEnum.bind.codeKey;
//    } else if (codeType == 4000) {
//      return VerifyCodeEnum.modifier.codeKey;
//    } else if (codeType == 4001) {
//      return VerifyCodeEnum.modifierPic.codeKey;
//    } else if (codeType == 4002 ){
//      return VerifyCodeEnum.modifierTele.codeKey;
//    } else if (codeType == 5000) {
//      return VerifyCodeEnum.withDraw.codeKey;
//    } else if (codeType == 6000) {
//      return VerifyCodeEnum.addAddress.codeKey;
//    } else if (codeType == 7000) {
//      return VerifyCodeEnum.createApiKey.codeKey;
//    } else if (codeType == 8000) {
//      return VerifyCodeEnum.updateWallet.codeKey;
//    }else if (codeType == 8001) {
//      return VerifyCodeEnum.addMerchant.codeKey;
//    }else if (codeType == 8002) {
//      return VerifyCodeEnum.adminLogin.codeKey;
//    }else if (codeType == 8003) {
//      return VerifyCodeEnum.resetPwd.codeKey;
//    } else if (codeType == 9000) {
//      return VerifyCodeEnum.updateAdmin.codeKey;
//    } else {
//      throw new RuntimeException("不支持的业务类型");
//    }
  }

  public static String getCodeName(Integer codeType){
    for (VerifyCodeEnum value : values()) {
      if (value.codeType.equals(codeType)){
        return value.codeName;
      }
    }
    throw new RuntimeException("不支持的业务类型");
//    if (codeType == 1000) {
//      return VerifyCodeEnum.register.codeName;
//    } else if (codeType == 1001) {
//      return VerifyCodeEnum.registerPic.codeName;
//    } else if (codeType == 2000) {
//      return VerifyCodeEnum.find.codeName;
//    } else if (codeType == 2001) {
//      return VerifyCodeEnum.findPic.codeName;
//    } else if (codeType == 3000) {
//      return VerifyCodeEnum.bind.codeName;
//    } else if (codeType == 4000) {
//      return VerifyCodeEnum.modifier.codeName;
//    } else if (codeType == 4001) {
//      return VerifyCodeEnum.modifierPic.codeName;
//    }else if (codeType == 4002) {
//      return VerifyCodeEnum.modifierTele.codeName;
//    } else if (codeType == 5000) {
//      return VerifyCodeEnum.withDraw.codeName;
//    } else if (codeType == 6000) {
//      return VerifyCodeEnum.addAddress.codeName;
//    } else if (codeType == 7000) {
//      return VerifyCodeEnum.createApiKey.codeName;
//    } else if (codeType == 8000) {
//      return VerifyCodeEnum.updateWallet.codeName;
//    } else if (codeType == 8001) {
//      return VerifyCodeEnum.addMerchant.codeName;
//    } else if (codeType == 8002) {
//      return VerifyCodeEnum.adminLogin.codeName;
//    } else if (codeType == 8003) {
//      return VerifyCodeEnum.resetPwd.codeName;
//    } else if (codeType == 9000) {
//      return VerifyCodeEnum.updateAdmin.codeName;
//    } else {
//      throw new RuntimeException("不支持的业务类型");
//    }
  }

}
