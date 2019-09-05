package com.andres.terraform.helpers

class CommandHelper {


    static getScript(version, command, values) {
        if (values == null) {
            values = [:]
        }
        def script = new StringBuilder()
        if (isInVersion(version, command.class.command)) {
            script.append(command.class.command.label)
            String key
            def value
            for (option in command.class.values()) {
                if (option != command.class.command && isInVersion(version, option)) {
                    key = option.label
                    if (option.mandatory || values.containsKey(key)) {
                        value = values.containsKey(key) ? values.get(key) : option.value
                        key = option != command.class.arguments ? key : ''
                        script.append(renderOption(command, option, key, value))
                    }
                }
            }
        }
        return script.toString()
    }

    private static renderOption(command, option, key, value) {
        String optionsSeparator = command.class.command.separator
        String valueSeparator = option.separator
        def optionString = new StringBuilder()
        if (option.repeatable && isCollectionOrArray(value)) {
            value.each {
                String item ->
                    if (!containsOptionLabel(command, item)) {
                        optionString.append(optionsSeparator)
                        optionString.append(key)
                        if (!option.empty) {
                            optionString.append(valueSeparator)
                            optionString.append(item.trim())
                        }
                    }
            }
        } else {
            if (!containsOptionLabel(command, value.toString())) {
                optionString.append(optionsSeparator)
                optionString.append(key)
                if (!option.empty) {
                    optionString.append(valueSeparator)
                    optionString.append(value.toString().trim())
                }
            }
        }
        return optionString.toString()
    }

    private static isCollectionOrArray(object) {
        [Collection, Object[]].any { it.isAssignableFrom(object.getClass()) }
    }

    private static containsOptionLabel(command, value) {
        for (option in command.class.values()) {
            if (value != null) {
                if (value.trim().equalsIgnoreCase(option.label)) {
                    return true
                }
                if (value.contains(option.label + option.separator)) {
                    return true
                }
                if (value.contains(option.label + command.separator)) {
                    return true
                }
            }
        }
        return false
    }

    private static isInVersion(version, option) {
        return version.between(option.from, option.to)
    }
}