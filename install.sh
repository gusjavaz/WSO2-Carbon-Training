. ./setenv.sh

#sudo apt-get update --yes
sudo apt-get install --yes apache2 libapache2-mod-fastcgi php-fpm

wget -nc -P $IMAGES --user-agent="testuser" --referer="http://connect.wso2.com/wso2/getform/reg/new_product_download" http://dist.wso2.org/products/carbon/$CARBON_VERSION/wso2carbon-$CARBON_VERSION.zip
wget -nc -P $IMAGES --user-agent="testuser" --referer="http://connect.wso2.com/wso2/getform/reg/new_product_download" http://dist.wso2.org/products/enterprise-service-bus/$ESB_VERSION/wso2esb-$ESB_VERSION.zip
wget -nc -P $IMAGES --user-agent="testuser" --referer="http://connect.wso2.com/wso2/getform/reg/new_product_download" http://dist.wso2.org/products/application-server/$AS_VERSION/wso2as-$AS_VERSION.zip

wget -nc -P $IMAGES http://product-dist.wso2.com/tools/svnkit-all-1.8.7.wso2v1.jar
wget -nc -P $IMAGES  http://maven.wso2.org/nexus/content/groups/wso2-public/com/trilead/trilead-ssh2/1.0.0-build215/trilead-ssh2-1.0.0-build215.jar

unzip -n -d ~/carbon-training/as1 $IMAGES/wso2as-$AS_VERSION.zip
unzip -n -d ~/carbon-training/esb1 $IMAGES/wso2esb-$ESB_VERSION.zip
unzip -n -d ~/carbon-training/esb2 $IMAGES/wso2esb-$ESB_VERSION.zip

sed -i -e's/#log4j.logger.org.apache.synapse.transport.http.wire=DEBUG/log4j.logger.org.apache.synapse.transport.http.wire=DEBUG/' ~/carbon-training/esb1/wso2esb-$ESB_VERSION/repository/conf/log4j.properties
sed -i -e's/#log4j.logger.org.apache.synapse.transport.http.wire=DEBUG/log4j.logger.org.apache.synapse.transport.http.wire=DEBUG/' ~/carbon-training/esb2/wso2esb-$ESB_VERSION/repository/conf/log4j.properties

