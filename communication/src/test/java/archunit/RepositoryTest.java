package archunit;

import archunit.common.ScopeClasses;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;
import org.junit.jupiter.api.Test;

public class RepositoryTest {
    private static GivenClassesConjunction repositories() {
        return ArchRuleDefinition.classes()
            .that()
            .haveNameMatching(".*Repository$");
    }

    private static GivenClassesConjunction repositoriesImpl() {
        return ArchRuleDefinition.classes()
            .that()
            .implement(JavaClass.Predicates.simpleNameEndingWith("Repository"));
    }

    @Test
    public void naming() {
        RepositoryTest.repositoriesImpl()
            .should()
            .haveNameMatching(".*(RepositoryImpl)")
            .check(ScopeClasses.CLASSES);
    }

    @Test
    public void beInterfaces() {
        RepositoryTest.repositories()
            .should()
            .beInterfaces();
    }

    @Test
    public void packageLocation() {
        RepositoryTest.repositories()
            .should()
            .resideInAPackage("..repository..")
            .check(ScopeClasses.CLASSES);

        RepositoryTest.repositoriesImpl()
            .should()
            .resideInAPackage("..repository.impl..")
            .check(ScopeClasses.CLASSES);
    }

    @Test
    public void finalFields() {
        RepositoryTest.repositoriesImpl()
            .should()
            .haveOnlyFinalFields()
            .check(ScopeClasses.CLASSES);
    }
}
