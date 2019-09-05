package com.andres.terraform.commands

import com.andres.terraform.utils.TerraformVersion

import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED
import static com.andres.terraform.utils.TerraformVersion.MAX_VERSION_SUPPORTED

enum Validate implements Command {
    command('terraform validate', false, false, '', ' ', true, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    check_variables('-check-variables', true, false, true, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    var('-var', false, true, null, ' ', true, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    var_file('-var-file', false, true, 'terraform.tfvars', '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    no_color('-no-color', false, false, null, null, false, true, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
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

    Validate(label, mandatory, editable, value, separator, repeatable, empty, from, to) {
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
