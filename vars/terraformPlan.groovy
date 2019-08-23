import com.andres.terraform.helpers.CommandHelper
import com.andres.terraform.commands.Plan

def call(Map options) {
    runScript(script: CommandHelper.getScript(Plan.command, options))
}