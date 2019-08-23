# terraform-utils
This repository contains the code of a Jenkins pipeline shared library that wraps the execution of terraform CLI commands.
The goal of the library is to provide a simple and powerful interface for developers to build Jenkins pipelines that cover most of the terraform CLI features.
This library also adds some command validations and restrictions that must be applied on a Jenkins pipeline context.

## Getting Started
Just clone the project and execute the following command:

```
./gradlew clean build
```

### Prerequisites
- Terraform installed on Jenkins (and its workers)
- ansiColor plugin installed on Jenkins
- Windows Jenkins worker should be tagged as 'windows'

### Installing
To install the library you should follow the instructions within [this page](https://jenkins.io/doc/book/pipeline/shared-libraries/#using-libraries).

## Usage

### Command Methods
This library currently supports the following methods:

* terraformApply - wraps 'terraform apply' command and its options
* terraformDestroy - wraps 'terraform destroy' command and its options
* terraformForceUnlock - wraps 'terraform force-unlock' command and its options
* terraformGet - wraps 'terraform get' command and its options
* terraformImport - wraps 'terraform import' command and its options
* terraformInit - wraps 'terraform init' command and its options
* terraformOutput - wraps 'terraform output' command and its options
* terraformPlan - wraps 'terraform plan' command and its options
* terraformRefresh - wraps 'terraform refresh' command and its options
* terraformTaint - wraps 'terraform taint' command and its options
* terraformValidate - wraps 'terraform validate' command and its options

All the precedent commands must be executed within a 'node' closure as they use some Jenkins specific features.

#### Command Methods Usage

All the command methods receives a map (it can be a null map), this map should contain the options and the arguments for the command. All options has the exact same syntax that the CLI options (dash included):


```
@Library('terraform-utils@master') _

node ('linux') {
    stage('checkout scm'){
        checkout scm
    }
    stage('terraform init'){
        terraformInit [:]
    }
    stage('terraform plan'){
        terraformPlan '-var':'my_name=Andres,my_last_name=Perez'
    }
}
```

The previous example clones a repo, initialize terraform and executes a plan providing a variable called 'my_name'.The generated scripts are:

```
...
terraform init -input=false -force-copy
...
terraform plan -input=false -out=terraform.tfplan -var my_name=Andres,my_last_name=Perez
...
```

As you can see the method add some additional options in order to avoid user inputs or any security issues.


#### Arguments

If you want to add arguments to the command you should use the 'arguments' option:

```
@Library('terraform-utils@master') _

node ('linux') {
    ...
    stage('terraform plan'){
        terraformPlan '-var':'my_name=Andres,my_last_name=Perez', arguments:"my_path"
    }
    ...
}
```

The generated script command is:

```
terraform plan -input=false -out=terraform.tfplan -var my_name=Andres,my_last_name=Perez my_path
```
Note: The argument will be ignored if you add supported options (e.g. '-no-color','my_path -no-color')

### withTerraform Context Method

Hera are some of the main features this method:

* Adds all existent environment variables to the terraform context.
* Adds user defined variables to the terraform context.
* Groups method so that they run within the same context.

Assuming that you want to pass the same variable to multiple methods:

```
@Library('terraform-utils@master') _

node ('linux') {
    ...
    stage('terraform init'){
        terraformInit [:]
    }
    stage('terraform plan'){
        terraformPlan '-var':'my_name=Andres,my_last_name=Perez'
    }
    stage('terraform apply'){
        terraformApply '-var':'my_name=Andres,my_last_name=Perez'
    }
    ...
}
```

Adding variables to multiple methods using withTerraform method:

```
@Library('terraform-utils@master') _

node ('linux') {
    ...
    def tfVars = [my_name:'Andres',my_last_name:'Perez']
    withTerraform(tfVars) {
        stage('terraform init'){
            terraformInit [:]
        }
        stage('terraform plan'){
            terraformPlan [:]
        }
        stage('terraform apply'){
            terraformApply [:]
        }
    }
    ...
}
```


## Built With

* [Jenkins](https://jenkins.io) - Integration server
* [Terraform](https://www.terraform.io) - Infrastructure as Code
* [IntelliJ CE](https://www.jetbrains.com/idea/) - IDE
* [Gradle](https://gradle.org) - Build tool
* [jUnit](https://junit.org/junit4/) - Test framework
* [Groovy](https://groovy.apache.org/index.html) - Development language

## Authors
* [Andres Perez](https://www.linkedin.com/in/eaperezm/)