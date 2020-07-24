#!/usr/bin/groovy
package com.workshop
 
def checkPrStatus(Pipeline p){
   def pr_check_response = httpRequest authentication: 'github-personal', httpMode: 'GET', url: "https://api.github.com/repos/${p.git_user}/${p.repository_name}/pulls/${p.pr_num}", validResponseCodes: '200,405,409', wrapAsMultipart: false
   def parsed_pr_check_response = readJSON text: "${pr_check_response.content}"
 
   if ("${parsed_pr_check_response['merged']}" == "true") {
       error "PR Already Merged"
   }
   println "PR is Open"
}
 
return this
