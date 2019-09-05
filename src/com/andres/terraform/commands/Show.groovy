package com.andres.terraform.commands


import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED
import static com.andres.terraform.utils.TerraformVersion.MAX_VERSION_SUPPORTED

enum Show implements Command {
    command('terraform show', false, false, '', ' ', true, false, new com.andres.terraform.utils.TerraformVersion(MIN_VERSION_SUPPORTED), new com.andres.terraform.utils.TerraformVersion(MAX_VERSION_SUPPORTED)),
    no_color('-no-color', false, false, null, null, false, true, new com.andres.terraform.utils.TerraformVersion(MIN_VERSION_SUPPORTED), new com.andres.terraform.utils.TerraformVersion(MAX_VERSION_SUPPORTED)),
    json('-json', false, false, null, null, false, true, new com.andres.terraform.utils.TerraformVersion(MIN_VERSION_SUPPORTED), new com.andres.terraform.utils.TerraformVersion("v0.11.99")),
    arguments('arguments', false, true, null, '', true, false, new com.andres.terraform.utils.TerraformVersion(MIN_VERSION_SUPPORTED), new com.andres.terraform.utils.TerraformVersion(MAX_VERSION_SUPPORTED));

    private final def label
    private final def mandatory
    private final def editable
    private final def value
    private final def separator
    private final def repeatable
    private final def empty
    private final def from
    private final def to

    Show(label, mandatory, editable, value, separator, repeatable, empty, from, to) {
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
