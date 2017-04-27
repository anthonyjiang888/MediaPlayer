package com.hss01248.safemedia;

import com.orhanobut.logger.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import es.dmoral.toasty.MyToast;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class ProxyTools {

    /**
     *
     * @param realObj
     * @param <T>
     * @return
     */
    public static <T> T getShowMethodTimeProxy(final T realObj){
      return (T) Proxy.newProxyInstance(ProxyTools.class.getClassLoader(), realObj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long time0 = System.currentTimeMillis();
                Object o = method.invoke(realObj,args);
                long time1 = System.currentTimeMillis() - time0;
               // Logger.e("ShowMethodTime :obj:"+ realObj + "--method:"+ method.getName() +"--time:"+time1);
                return o;
            }
        });

    }


    public static <T> T getShowMethodInfoProxy(final T realObj){
        return (T) Proxy.newProxyInstance(ProxyTools.class.getClassLoader(), realObj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

               if(!"onBufferingUpdate".equals(method.getName()) && !"onProgress".equals(method.getName())){
                   Logger.e("method name:"+ method.getName() + "--args:"+ Arrays.toString(args));
                   MyToast.info(method.getName());
               }

                Object o = method.invoke(realObj,args);
                return o;
            }
        });
    }





}
