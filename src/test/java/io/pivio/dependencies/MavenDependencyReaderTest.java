package io.pivio.dependencies;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MavenDependencyReaderTest {

    private MavenDependencyReader mavenLicenseReader;

    @Before
    public void setUp() throws Exception {
        mavenLicenseReader = new MavenDependencyReader();
    }

    @Test
    public void testReadDependency() throws Exception {
        String licenseFile = "src/test/resources/mvn-licenses-one.xml";

        List<Dependency> dependencies = mavenLicenseReader.readDependencies(new File(licenseFile));

        assertThat(dependencies.get(0).name).isEqualTo("asm:asm");
    }

    @Test
    public void testReadDependencyWithTwoLicenses() throws Exception {
        String licenseFile = "src/test/resources/mvn-licenses-one.xml";

        List<Dependency> dependencies = mavenLicenseReader.readDependencies(new File(licenseFile));

        Assertions.assertThat(dependencies.get(0).getLicenses()).hasSize(2);
    }

    @Test
    public void testReadMultipleDependencies() throws Exception {
        String licenseFile = "src/test/resources/mvn-licenses.xml";

        List<Dependency> dependencies = mavenLicenseReader.readDependencies(new File(licenseFile));

        assertThat(dependencies).hasSize(140);
    }

    @Test
    public void testTryToReadNonExistingFile() throws Exception {
        String licenseFile = "src/nonexisting";

        List<Dependency> dependencies = mavenLicenseReader.readDependencies(new File(licenseFile));

        assertThat(dependencies).hasSize(0);
    }

}