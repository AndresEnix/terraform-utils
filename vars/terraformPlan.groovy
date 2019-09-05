import com.andres.terraform.commands.Plan

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(terraformVersion(), Plan.command, options))
}