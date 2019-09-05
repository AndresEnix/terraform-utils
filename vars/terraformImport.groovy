import com.andres.terraform.commands.Import

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(terraformVersion(), Import.command, options))
}