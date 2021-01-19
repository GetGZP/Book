package com.AliPay;
import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfIg {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000116680549";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCOG5aaZiMEF4Wu0bagJ4dIYxK2Yo9VJXPokXKfoZEz0lZeLIr2RYgIiBKsYdCow7gsjQnOL0OfzX6XQEFimDuo0/carDKqPkjDL7NNPHBRo6BfBIjGAEcaSNF+uwjPkOpC/CGsqSkE0KTzyhn4YKM1Jniq+6oMW82cxclMYZWMGKETccE56GIoP8DgkHlj0sRHvx712NLUaYiESTIC3WfszEteSA3uryxXokhJcP8Sxs2/g2ovGaCZkOI8cP0qrGl2rPbOLEmDNTESnikLly8kAXMFsidqxsgqnCpGtuC5KYkUwBMM3vadbvxY1fX489GAxv/fwW3NkcT81s5l9JHzAgMBAAECggEAE3sEsjW+JxlyYfytpFbG3M14CziHnYx8zyzhIbDsTsZOwKoqgogVldXReYAPMpNh8/mBlpo0EvwAlotgazujMgmcnjNs3pmbL+aiFbxrSUW8/kmI1lvDY38H+O96zbl346bclua5kMdpVWInHT1iIi8b65SEQx1ymkMizS2zanjhUIB1ZMqQNDNhsJqsXgovT8MiUxzVGqzflDTD84AyUgWw4P1Ivy6MeSGUefj26x/umQIJoRlR/L4PJsx4jmBOcK+UBM5ldplOwLouEzjfwbymzgLShXWYOTv0PyuqJC2WuGGA8+d1BSLRNuG4O2UVbvziD1hp/oPBroCYymq7gQKBgQDSWk0L0St5tzUQskT6TcBn8iNIqtPgPyeu17PRgjRPP6SmInZ9LTfFMpc+9/GaIAQBeUzJc8BndGm0YNFtcJ1XHP2I6wOQgEd2d3/u3FGeEEnTl6+8VAtXoFbEuMmAXQ7pyHJj34oiPhhg0y0R2npc2mtT5/Zlezi6aOLlw6YRmwKBgQCs8hVEaz8owwFm5E+vnx4P6XxRq3iEffLpX2ixMXCfyz7QlXWl1C3lN1XgLyYCH+4PuVDEqQcRw1tFbWo8qv0nS2FVJIPXpNDadmPKGhnw+WBMYEkSLRGswVGM5w/9M4jfabQ6iPW+W3e1dhMlZecUyk9U/o+5ek4X9phJ4yzSiQKBgQC3RClgJErmbnHzK/3gvaDmbxXCl+KkuyFNuWxXGkOMz/wZKmllQeIXEq6nE8oQ59fVmOMHmKuG9uupVbjgyn6qiP1zLp4aZUjds9toNdd+CN7pVHytbeO74lv2ZLBZ/7mggRz4Wqe0kbkXkqTvqxN0lm9v2ObZH3QbJIqad8zWBQKBgFTy6A1eXHyqJIMkvBt4neyU9FKtVhpGP03wZl06+qYS0DzmXQ5iRVTktFLU9edvRaEFlq2ylN1dxvQ4RUmdd5GYjiujLvt6YngcVPHNllZuN6XsWZgK+TaREnySdqEZci7DHhrucT7acNd6MvdYsYPdx8/o5jHl6HwOzUjlxHt5AoGBAI5iObmLSm2ojF3C0aBeTln43ginb0foBfOzsKgp3DyILX+GX6a90TWgCSxdzzjyIwIj0NV2NtJhd/tM7ijWFPiGTd7YmhgUTwaBSD9nAxbw5oZFwukW96a4y/RowUxAmzmfTUSqQgec+vsy2cG9FygxDa4VZ2R7mT0fzKxBgb/L";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAirGT1pBfAhmUYTp/2iXI6DM0uev0AVeR0Cr3JcYrg4V3rKjRhB7EV15gA0jy5md9unaKPrvSweqEfvqoL6CjnoOtgxvLF6/pVOMgj9F10ZTZknsZ6XPZdfjGTZFqd2NmkL1ZVcVRTov3AMz55z4+nepHqZMF5Cy6o3q2VSIO/ivagXNmu3g5y51JVxpdl0d/6KdHs/5Cjy5prB1PIgnea4tBeP3j+tppnTWzJvMEK0raXTswGw3yp8MMoA3ztvtjVkrgu6bRygm5GfCH6evPtEgIJICyuBFctWRQeAyqw+czTxFYHFxzj+GGuSzkg4R/S4Xt9T42q7SsY6nWB+bndwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://192.168.9.236:8080/sss.html";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://192.168.9.236:8080/sss.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
//    public static String log_path = "C:\\";

}

