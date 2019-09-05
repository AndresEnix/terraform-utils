package com.andres.terraform.commands

import com.andres.terraform.utils.TerraformVersion

import static com.andres.terraform.utils.TerraformVersion.MIN_VERSION_SUPPORTED
import static com.andres.terraform.utils.TerraformVersion.MAX_VERSION_SUPPORTED

enum Init implements Command {
    command('terraform init', true, false, '', ' ', false, true, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    input('-input', true, false, false, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    force_copy('-force-copy', true, false, null, null, false, true, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    backend('-backend', false, false, true, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    backend_config('-backend-config', false, true, null, '=', true, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    from_module('-from-module', false, true, null, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    get('-get', false, true, true, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    get_plugins('-get-plugins', false, true, true, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    lock_timeout('-lock-timeout', false, true, '0s', '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    lock('-lock', false, true, true, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    no_color('-no-color', false, false, null, null, false, true, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    plugin_dir('-plugin-dir', false, true, null, '=', true, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    reconfigure('-reconfigure', false, false, null, null, false, true, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    upgrade('upgrade', false, true, false, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
    verify_plugins('-verify-plugins', false, true, true, '=', false, false, new TerraformVersion(MIN_VERSION_SUPPORTED), new TerraformVersion(MAX_VERSION_SUPPORTED)),
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

    Init(label, mandatory, editable, value, separator, repeatable, empty, from, to) {
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
