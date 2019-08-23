package com.andres.terraform.commands

enum Refresh implements Command {
    command('refresh', false, false, '', ''),
    empty_option('', false, false, '', '='),
    backup('-backup', true, true, null, '='),
    input('-input', false, true, false, '='),
    lock('-lock', true, true, true, '='),
    lock_timeout('-lock-timeout', true, true, '0s', '='),
    no_color('-no-color', true, false, null, '='),
    state('-state', true, true, 'terraform.tfstate', '='),
    state_out('-state-out', true, true, 'terraform.tfstate', '='),
    target('-target', true, true, null, '='),
    var('-var', true, true, null, ' '),
    var_file('-var-file', true, true, null, '=');

    private final String label
    private final Boolean enabled
    private final Boolean editable
    private final String value
    private final String separator

    Refresh(label, enabled, editable, value, separator) {
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
        return [Refresh.input]
    }
}
