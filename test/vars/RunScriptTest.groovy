package vars

import com.lesfurets.jenkins.unit.BaseRegressionTest
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class RunScriptTest extends BaseRegressionTest {

    @Override
    @Before
    void setUp() throws Exception {
        super.setUp()

        callStackPath = "test/vars/callstacks/"

        binding.setVariable('scm', [:])
        binding.setVariable('env', [NODE_NAME: 'unix'])

        helper.registerAllowedMethod('library', [String.class], null)
        helper.registerAllowedMethod('powershell', [Map.class], null)
        helper.registerAllowedMethod('ansiColor', [String.class], null)
    }

    @Test
    @Ignore
    void runScript_runShOnLinuxNode() throws Exception {
        binding.setVariable('env', [NODE_NAME: 'linux'])
        def script = loadScript('vars/runScript.groovy')
        script.call(null)

        testNonRegression("linux")
    }

    @Test
    @Ignore
    void runScript_runPowerShellOnWindowsNode() throws Exception {
        binding.setVariable('env', [NODE_NAME: 'windows'])
        def script = loadScript('vars/runScript.groovy')
        script.call(null)

        testNonRegression("windows")
    }

    @Test
    @Ignore
    void runScript_returnValue() throws Exception {
        binding.setVariable('env', [NODE_NAME: 'windows'])
        def script = loadScript('vars/runScript.groovy')
        def actual = script.call([
                script      : 'ls -l',
                encoding    : 'ISO-8859-1',
                label       : 'My label',
                returnStatus: true,
                returnStdout: true
        ])

        testNonRegression("return")
        Assert.assertNotNull(actual)
    }
}