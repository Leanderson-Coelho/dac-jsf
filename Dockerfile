FROM payara/server-full
COPY target/dac-jsf.war $DEPLOY_DIR
