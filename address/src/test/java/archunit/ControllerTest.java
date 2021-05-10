package archunit;

import archunit.common.ScopeClasses;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RestController;

public class ControllerTest {
    private static GivenClassesConjunction controllers() {
        return ArchRuleDefinition.classes()
            .that()
            .areAnnotatedWith(RestController.class);
    }

    @Test
    public void naming() {
        ControllerTest.controllers()
            .should()
            .haveNameMatching(".*(Controller)")
            .check(ScopeClasses.CLASSES);
    }

    @Test
    public void packageLocation() {
        ControllerTest.controllers()
            .should()
            .resideInAPackage("..controller..")
            .check(ScopeClasses.CLASSES);
    }

    @Test
    public void finalFields() {
        ControllerTest.controllers()
            .should()
            .haveOnlyFinalFields()
            .check(ScopeClasses.CLASSES);
    }
}
