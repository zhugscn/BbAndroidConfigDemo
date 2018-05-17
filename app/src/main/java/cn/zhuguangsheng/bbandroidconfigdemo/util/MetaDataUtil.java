package cn.zhuguangsheng.bbandroidconfigdemo.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * AndroidManifest的meta data获取类
 */
public class MetaDataUtil {
    private static final String TAG = MetaDataUtil.class.getSimpleName();

    /**
     * 支付方式选项
     */
    public final static String META_KEY_PAY_TYPE = "PAYTYPE";

    private static Context context;

    public static void init(Context appContext){
        context = appContext;
    }

    /**
     * 获取指定的meta String信息
     * 对了规范对meta信息的读取，设为private不对外开放,具体获取的meta请写函数包装
     * @param metaName
     * @return
     * @throws Exception
     */
    private static String getMetaStringData(String metaName) throws Exception {
        if(context==null){
            throw new Exception("meta上下文不存在");
        }

        ApplicationInfo appInfo;
        String metaString="";
        try {
            appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            metaString = appInfo.metaData.getString(metaName);
            Log.i(TAG, "metaString=" + metaString);

            if(metaString!=null) {
                return metaString;
            }else{
                Log.e(TAG, "not define meta: " + metaName);
                throw new Exception("未定义meta:" + metaName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("获取meta失败");
        }
    }

    /**
     * 获取支付方式
     * @return
     * @throws Exception
     */
    public static String getPayType() throws Exception {
        return getMetaStringData(META_KEY_PAY_TYPE);
    }

}

