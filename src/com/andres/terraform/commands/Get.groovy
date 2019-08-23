package com.andres.terraform.commands

enum Get implements Command {
    command('get', false, false, '', ''),
    empty_option('', false, false, '', '='),
    update('-update', true, true, false, '='),
    no_color('-no-color', true, false, null, '=');

    private final String label
    private final Boolean enabled
    private final Boolean editable
    private final String value
    private final String separator

    Get(label, enabled, editable, value, separator) {
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
        return []
    }
}
