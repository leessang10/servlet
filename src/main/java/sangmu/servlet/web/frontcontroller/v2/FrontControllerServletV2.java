package sangmu.servlet.web.frontcontroller.v2;

import sangmu.servlet.web.frontcontroller.MyView;
import sangmu.servlet.web.frontcontroller.v1.Controller.MemberFormControllerV1;
import sangmu.servlet.web.frontcontroller.v1.Controller.MemberListControllerV1;
import sangmu.servlet.web.frontcontroller.v1.Controller.MemberSaveControllerV1;
import sangmu.servlet.web.frontcontroller.v1.ControllerV1;
import sangmu.servlet.web.frontcontroller.v2.Controller.MemberFormControllerV2;
import sangmu.servlet.web.frontcontroller.v2.Controller.MemberListControllerV2;
import sangmu.servlet.web.frontcontroller.v2.Controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        // ControllerV2 controller = new Member####ControllerV2();
        ControllerV2 controller = controllerMap.get(requestURI);
        // 존재하지 않는 Controller에 대한 404 Page Not Found 예외 처리
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
