package sangmu.servlet.web.frontcontroller.v4;

import sangmu.servlet.web.frontcontroller.MyView;
import sangmu.servlet.web.frontcontroller.v4.Controller.MemberFormControllerV4;
import sangmu.servlet.web.frontcontroller.v4.Controller.MemberListControllerV4;
import sangmu.servlet.web.frontcontroller.v4.Controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        // ControllerV3 controller = new Member####ControllerV3();
        ControllerV4 controller = controllerMap.get(requestURI);
        // 존재하지 않는 Controller에 대한 404 Page Not Found 예외 처리
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // paramMap, model 생성
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
        
        String viewName = controller.process(paramMap, model);
        // View Resolver
        MyView view = viewResolver(viewName);
        view.render(model, request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
