package com.andres.terraform.utils


import com.andres.terraform.commands.Show
import org.junit.Assert
import org.junit.Test

class TerraformVersionTest {

    @Test(expected = IllegalArgumentException.class)
    void terraformVersion_throwsIllegalArgumentExceptionOnNull() throws Exception {
        new TerraformVersion(null)
    }

    @Test(expected = IllegalArgumentException.class)
    void terraformVersion_throwsIllegalArgumentExceptionOnNoValidFormat() throws Exception {
        new TerraformVersion("TEST")
    }

    @Test
    void get_returnsVersionWithoutV() throws Exception {
        setup:
        def expectedVersion = "0.12.6"

        when:
        def actualVersion = new TerraformVersion("v0.12.6")

        then:
        Assert.assertEquals(expectedVersion, actualVersion.get())
    }

    @Test
    void equals_twoVersionsAreEqual() throws Exception {
        setup:
        def baseVersion = new TerraformVersion("v0.12.6")
        def actualVersion = new TerraformVersion("v0.12.6")

        when:
        def result = actualVersion.equals(baseVersion)

        then:
        Assert.assertTrue(result)
    }

    @Test
    void equals_notEqualsToNull() throws Exception {
        setup:
        def actualVersion = new TerraformVersion("v0.12.6")

        when:
        def result = actualVersion.equals(null)

        then:
        Assert.assertFalse(result)
    }

    @Test
    void equals_isCheckingClass() throws Exception {
        setup:
        def actualVersion = new TerraformVersion("v0.12.6")

        when:
        def result = actualVersion.equals(new String())

        then:
        Assert.assertFalse(result)
    }

    @Test
    void compareTo_isZero() throws Exception {
        setup:
        def baseVersion = new TerraformVersion("v0.12.6")
        def actualVersion = new TerraformVersion("v0.12.6")

        when:
        def result = actualVersion.compareTo(baseVersion)

        then:
        Assert.assertEquals(0, result)
    }

    @Test
    void compareTo_isNegative() throws Exception {
        setup:
        def baseVersion = new TerraformVersion("v0.12.6")
        def actualVersion = new TerraformVersion("v0.11.11")

        when:
        def result = actualVersion.compareTo(baseVersion)

        then:
        Assert.assertEquals(-1, result)
    }

    @Test
    void compareTo_isPositive() throws Exception {
        setup:
        def baseVersion = new TerraformVersion("v0.12.6")
        def actualVersion = new TerraformVersion("v0.13.11")

        when:
        def result = actualVersion.compareTo(baseVersion)

        then:
        Assert.assertEquals(1, result)
    }

    @Test
    void compareTo_isPositiveWhenNull() throws Exception {
        setup:
        def actualVersion = new TerraformVersion("v0.13.11")

        when:
        def result = actualVersion.compareTo(null)

        then:
        Assert.assertEquals(1, result)
    }

    @Test
    void between_maxCommandVersionEqualsEnvironmentVersion() throws Exception {
        setup:
        com.andres.terraform.commands.Command show = Show.command
        def environmentVersion = new TerraformVersion("v0.12.7")
        def fromVersion = show.from
        def toVersion = show.to

        when:
        def result = environmentVersion.between(fromVersion, toVersion)

        then:
        Assert.assertTrue(result)
    }

    @Test
    void between_mimCommandVersionEqualsEnvironmentVersion() throws Exception {
        setup:
        com.andres.terraform.commands.Command show = Show.command
        def environmentVersion = new TerraformVersion("v0.11.11")
        def fromVersion = show.from
        def toVersion = show.to

        when:
        def result = environmentVersion.between(fromVersion, toVersion)

        then:
        Assert.assertTrue(result)
    }

    @Test
    void between_environmentVersionInCommandRange() throws Exception {
        setup:
        com.andres.terraform.commands.Command show = Show.command
        def environmentVersion = new TerraformVersion("v0.11.17")
        def fromVersion = show.from
        def toVersion = show.to

        when:
        def result = environmentVersion.between(fromVersion, toVersion)

        then:
        Assert.assertTrue(result)
    }

    @Test
    void between_environmentVersionHigherThanMaxCommandVersion() throws Exception {
        setup:
        com.andres.terraform.commands.Command show = Show.command
        def environmentVersion = new TerraformVersion("v0.12.8")
        def fromVersion = show.from
        def toVersion = show.to

        when:
        def result = environmentVersion.between(fromVersion, toVersion)

        then:
        Assert.assertFalse(result)
    }

    @Test
    void between_environmentVersionLowerThanMinCommandVersion() throws Exception {
        setup:
        com.andres.terraform.commands.Command show = Show.command
        def environmentVersion = new TerraformVersion("v0.10.99")
        def fromVersion = show.from
        def toVersion = show.to

        when:
        def result = environmentVersion.between(fromVersion, toVersion)

        then:
        Assert.assertFalse(result)
    }
}