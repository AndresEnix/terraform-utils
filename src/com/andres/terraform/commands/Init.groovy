package com.andres.terraform.commands

enum Init implements Command {
    command('init', false, false, '', ''),
    empty_option('', false, false, '', '='),
    backend('-backend', true, true, null, '='),
    backend_config('-backend-config', true, true, null, '='),
    force_copy('-force-copy', false, false, null, '='),
    from_module('-from-module', true, true, null, '='),
    get('-get', true, true, null, '='),
    get_plugins('-get-plugins', true, true, null, '='),
    input('-input', false, true, false, '='),
    lock('-lock', true, true, true, '='),
    lock_timeout('-lock-timeout', true, true, '0s', '='),
    no_color('-no-color', true, false, null, '='),
    plugin_dir('-plugin-dir', true, true, null, '='),
    reconfigure('-reconfigure', true, false, null, '='),
    upgrade('upgrade', true, true, null, '='),
    verify_plugins('-verify-plugins', true, true, null, '=');

    private final String label
    private final Boolean enabled
    private final Boolean editable
    private final String value
    private final String separator

    Init(label, enabled, editable, value, separator) {
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
        return [Init.input, Init.force_copy]
    }
}
