package com.andres.terraform.commands

enum ForceUnlock implements Command {
    command('force-unlock', false, false, '', ''),
    empty_option('', false, false, '', '='),
    force('-force', false, false, null, '=');

    private final String label
    private final Boolean enabled
    private final Boolean editable
    private final String value
    private final String separator

    ForceUnlock(label, enabled, editable, value, separator) {
        this.label = label
        this.enabled = enabled
        this.editable = editable
        this.value = value
        this.separator = separator
    }

    String getLabel() {
        return label
    }

    Boolean getEnabled() {
        return enabled
    }

    Boolean getEditable() {
        return editable
    }

    String getValue() {
        return value
    }

    String getSeparator() {
        return separator
    }

    def getCommand() {
        return BASE_SCRIPT + ' ' + command.label
    }

    def getCustomizable() {
        return true
    }

    def getEmptyOption() {
        return empty_option
    }

    def getMandatoryOptions() {
        return [ForceUnlock.force]
    }
}
