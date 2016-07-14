package ro.teamnet.zth.api.annotations;

        import java.lang.annotation.*;

/**
 * Created by user on 7/14/2016.
 */


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface MyController {

    String urlPath();

}
