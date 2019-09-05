package com.andres.terraform.commands

import com.andres.terraform.utils.TerraformVersion

import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED
import static com.andres.terraform.utils.TerraformVersion.MAX_VERSION_SUPPORTED

enum Refresh implements Command {
    command('terraform refresh', false, false, '', ' ', true, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    backup('-backup', false, true, null, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    input('-input', true, false, false, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    lock_timeout('-lock-timeout', false, true, '0s', '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    lock('-lock', false, true, true, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    no_color('-no-color', false, false, null, null, false, true, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    state('-state', false, true, 'terraform.tfstate', '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    state_out('-state-out', false, true, 'new_terraform.tfstate', '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    target('-target', false, true, null, '=', true, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    var('-var', false, true, null, ' ', true, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    var_file('-var-file', false, true, 'terraform.tfvars', '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    arguments('arguments', false, true, null, '', true, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED));

    private final def label
    private final def mandatory
    private final def editable
    private final def value
    private final def separator
    private final def repeatable
    private final def empty
    private final def from
    private final def to

    Refresh(label, mandatory, editable, value, separator, repeatable, empty, from, to) {
        this.label = label
        this.mandatory = mandatory
        this.editable = editable
        this.value = value
        this.separator = separator
        this.repeatable = repeatable
        this.empty = empty
        this.from = from
        this.to = to
    }
}
