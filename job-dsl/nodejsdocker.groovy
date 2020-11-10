job('NodeJS Docker example') {
    scm {
        git('git://github.com/StephaneHe/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('SH')
            node / gitConfigEmail('stephane.hercot@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('sherca/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('sherca')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
