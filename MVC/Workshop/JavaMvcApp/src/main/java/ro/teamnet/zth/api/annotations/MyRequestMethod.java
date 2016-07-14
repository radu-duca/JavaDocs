package ro.teamnet.zth.api.annotations;

import java.lang.annotation.*;

/**
 * Created by user on 7/14/2016.
 */


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface MyRequestMethod {
    String methodType() default "GET";
    String urlPath();



}
