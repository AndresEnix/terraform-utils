package com.andres.terraform.commands

enum Output implements Command {
    command('output', false, false, '', ''),
    empty_option('', false, false, '', '='),
    state('-state', true, true, 'terraform.tfstate', '='),
    no_color('-no-color', true, false, null, '='),
    module('-module', true, true, null, '='),
    json('-json', true, false, null, '=');

    private final String label
    private final Boolean enabled
    private final Boolean editable
    private final String value
    private final String separator

    Output(label, enabled, editable, value, separator) {
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
