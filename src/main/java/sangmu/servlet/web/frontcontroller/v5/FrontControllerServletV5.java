package sangmu.servlet.web.frontcontroller.v5;

import sangmu.servlet.web.frontcontroller.ModelView;
import sangmu.servlet.web.frontcontroller.MyView;
import sangmu.servlet.web.frontcontroller.v3.Controller.MemberFormControllerV3;
import sangmu.servlet.web.frontcontroller.v3.Controller.MemberListControllerV3;
import sangmu.servlet.web.frontcontroller.v3.Controller.MemberSaveControllerV3;
import sangmu.servlet.web.frontcontroller.v5.Adaptor.ControllerV3HandlerAdaptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdaptor> handlerAdaptors = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHanderAdaptors();
    }

    private boolean initHanderAdaptors() {
        return handlerAdaptors.add(new ControllerV3HandlerAdaptor());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);

        // 존재하지 않는 Controller에 대한 404 Page Not Found 예외 처리
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdaptor adaptor = getHandlerAdaptor(handler);
        ModelView mv = adaptor.handle(request, response, handler);

        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), request, response);
    }

    private MyHandlerAdaptor getHandlerAdaptor(Object handler) {
        for (MyHandlerAdaptor handlerAdaptor : handlerAdaptors) {
            if (handlerAdaptor.supports(handler)) {
                return handlerAdaptor;
            }
        }
        throw new IllegalArgumentException("handler adaptor를 찾을 수 없습니다. handler: " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
