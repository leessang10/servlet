package sangmu.servlet.web.frontcontroller.v5.Adaptor;

import sangmu.servlet.web.frontcontroller.ModelView;
import sangmu.servlet.web.frontcontroller.v3.ControllerV3;
import sangmu.servlet.web.frontcontroller.v5.MyHandlerAdaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdaptor implements MyHandlerAdaptor {
    @Override
    public boolean supports(Object handler) {
        // ContorllerV3를 처리할 수 있는 어댑터
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // 핸들러를 V3로 변환한다
        ControllerV3 controller = (ControllerV3) handler;
        // V3 형식에 맞게
        Map<String, String> paramMap = createParamMap(request);

        ModelView modelView = controller.process(paramMap);
        return modelView;
    }
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
