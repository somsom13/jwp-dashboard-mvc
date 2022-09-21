package nextstep.mvc.controller.asis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import nextstep.mvc.controller.tobe.AnnotationHandlerMapping;
import nextstep.mvc.controller.tobe.HandlerExecution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandlerControllerAdapterTest {

    @Test
    @DisplayName("HandlerControllerAdapter에서 지원하는 경우 handler일 경우 true")
    void isTrueSupports() {
        final HandlerControllerAdapter handlerControllerAdapter = new HandlerControllerAdapter();
        final Controller forwardController = new ForwardController("/");

        final boolean actual = handlerControllerAdapter.supports(forwardController);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("HandlerControllerAdapter에서 지원하지 않는 경우 handler일 경우 false")
    void isFalseSupports() {
        final HandlerControllerAdapter handlerControllerAdapter = new HandlerControllerAdapter();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final AnnotationHandlerMapping annotationHandlerMapping = new AnnotationHandlerMapping("samples");
        annotationHandlerMapping.initialize();

        when(request.getRequestURI()).thenReturn("/get-test");
        when(request.getMethod()).thenReturn("GET");

        final HandlerExecution handlerExecution = annotationHandlerMapping.getHandler(request);
        final boolean actual = handlerControllerAdapter.supports(handlerExecution);

        assertThat(actual).isFalse();
    }
}