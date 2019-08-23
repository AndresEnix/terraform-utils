package com.andres.terraform.helpers

import com.andres.terraform.commands.Command

class CommandHelper {

    static getScript(command, options) {
        if (options == null) {
            options = [:]
        }
        String spaceString = ' '
        StringBuilder builder = new StringBuilder(command.getCommand())
        for (option in command.getMandatoryOptions()) {
            builder.append(spaceString)
            builder.append(option.label)
            if (option.editable) {
                builder.append(option.separator)
                if (option.enabled && options.containsKey(option.label)) {
                    builder.append(options.get(option.label).trim())
                } else {
                    builder.append(option.value)
                }
            }
            options.remove(option.label)
        }
        options.each { key, val ->
            def option = findOption(command, key)
            if (option.enabled) {
                builder.append(spaceString)
                builder.append(key)
                if (option.editable) {
                    builder.append(option.separator)
                    builder.append(val.trim())
                }
            }
        }
        if (command.getCustomizable() && options.arguments != null) {
            if (CommandHelper.isCollectionOrArray(options.arguments)) {
                options.arguments.each { argument ->
                    if (!argumentContainsOptionLabel(command, argument)) {
                        builder.append(spaceString)
                        builder.append(argument.trim())
                    }
                }
            } else {
                if (!argumentContainsOptionLabel(command, options.arguments)) {
                    builder.append(spaceString)
                    builder.append(options.arguments.trim())
                }
            }
        }
        return builder.toString()
    }

    static def argumentContainsOptionLabel(command, argument) {
        for (Command option in command.class.values()) {
            if (option != command.class.command && option != command.class.empty_option) {
                if (argument.contains(option.label)) {
                    return true
                }
            }
        }
        return false
    }

    static def findOption(command, val) {
        for (Command option in command.class.values()) {
            if (option.label == val) {
                return option
            }
        }
        return command.empty_option
    }

    static boolean isCollectionOrArray(object) {
        [Collection, Object[]].any { it.isAssignableFrom(object.getClass()) }
    }
}