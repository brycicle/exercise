package archunit;

import archunit.common.ScopeClasses;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

public class ServiceTest {
    private static GivenClassesConjunction services() {
        return ArchRuleDefinition.classes()
            .that()
            .haveNameMatching(".*Service$");
    }

    private static GivenClassesConjunction servicesImpl() {
        return ArchRuleDefinition.classes()
            .that()
            .areAnnotatedWith(Service.class);
    }

    @Test
    public void naming() {
        ServiceTest.servicesImpl()
            .should()
            .haveNameMatching(".*(ServiceImpl)")
            .check(ScopeClasses.CLASSES);
    }

    @Test
    public void beInterfaces() {
        ServiceTest.services()
            .should()
            .beInterfaces();
    }

    @Test
    public void packageLocation() {
        ServiceTest.services()
            .should()
            .resideInAPackage("..service..")
            .check(ScopeClasses.CLASSES);

        ServiceTest.servicesImpl()
            .should()
            .resideInAPackage("..service.impl..")
            .check(ScopeClasses.CLASSES);
    }

    @Test
    public void finalFields() {
        ServiceTest.servicesImpl()
            .should()
            .haveOnlyFinalFields()
            .check(ScopeClasses.CLASSES);
    }
}
