package com.andres.terraform.commands

interface Command {

    static final def BASE_SCRIPT = 'terraform'

    def getLabel();

    def getEnabled();

    def getEditable();

    def getValue();

    def getSeparator();

    def getCommand();

    def getCustomizable();

    def getEmptyOption();

    def getMandatoryOptions();
}
