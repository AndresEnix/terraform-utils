import com.andres.terraform.commands.Get

import static com.andres.terraform.helpers.CommandHelper.getScript

def call(Map options) {
    runScript(script: getScript(terraformVersion(), Get.command, options))
}