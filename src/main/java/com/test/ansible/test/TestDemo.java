package com.test.ansible.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.test.ansible.pojo.events.EventLog;
import com.test.ansible.pojo.events.Events;
import com.test.ansible.pojo.launch.LaunchResult;
import com.test.ansible.pojo.status.Activity;
import com.test.ansible.util.HttpClientUtil;
import com.test.ansible.util.Inputs;
import com.test.ansible.util.TestHttp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDemo {

    private static String createCredentialsUrl = "https://ansible/api/v2/credentials/";
    private static String createInventoriesUrl = "https://ansible/api/v2/inventories/";
    private static String createHostsUrl = "https://ansible/api/v2/hosts/";
    private static String createProjectsUrl = "https://ansible/api/v2/projects/";
    private static String createJobTemplateUrl = "https://ansible/api/v2/job_templates/";

    private static String launchJobTemlateUrl = "https://ansible/api/v2/job_templates/11/launch/";

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.trustStore","C:\\Program Files\\Java\\jre1.8.0_181\\lib\\security\\jssecacerts");

//        String result = "{\"job\":31,\"ignored_fields\":{},\"id\":31,\"type\":\"job\",\"url\":\"/api/v2/jobs/31/\",\"related\":{\"created_by\":\"/api/v2/users/1/\",\"modified_by\":\"/api/v2/users/1/\",\"labels\":\"/api/v2/jobs/31/labels/\",\"inventory\":\"/api/v2/inventories/3/\",\"project\":\"/api/v2/projects/6/\",\"credential\":\"/api/v2/credentials/3/\",\"extra_credentials\":\"/api/v2/jobs/31/extra_credentials/\",\"credentials\":\"/api/v2/jobs/31/credentials/\",\"unified_job_template\":\"/api/v2/job_templates/7/\",\"stdout\":\"/api/v2/jobs/31/stdout/\",\"job_events\":\"/api/v2/jobs/31/job_events/\",\"job_host_summaries\":\"/api/v2/jobs/31/job_host_summaries/\",\"activity_stream\":\"/api/v2/jobs/31/activity_stream/\",\"notifications\":\"/api/v2/jobs/31/notifications/\",\"job_template\":\"/api/v2/job_templates/7/\",\"cancel\":\"/api/v2/jobs/31/cancel/\",\"create_schedule\":\"/api/v2/jobs/31/create_schedule/\",\"relaunch\":\"/api/v2/jobs/31/relaunch/\"},\"summary_fields\":{\"inventory\":{\"id\":3,\"name\":\"my-first-inv\",\"description\":\"\",\"has_active_failures\":false,\"total_hosts\":2,\"hosts_with_active_failures\":0,\"total_groups\":0,\"groups_with_active_failures\":0,\"has_inventory_sources\":false,\"total_inventory_sources\":0,\"inventory_sources_with_failures\":0,\"organization_id\":1,\"kind\":\"\"},\"project\":{\"id\":6,\"name\":\"my-first-demo\",\"description\":\"\",\"status\":\"ok\",\"scm_type\":\"\"},\"credential\":{\"id\":3,\"name\":\"test_cred\",\"description\":\"\",\"kind\":\"ssh\",\"cloud\":false,\"credential_type_id\":1},\"job_template\":{\"id\":7,\"name\":\"my-first-template\",\"description\":\"\"},\"unified_job_template\":{\"id\":7,\"name\":\"my-first-template\",\"description\":\"\",\"unified_job_type\":\"job\"},\"created_by\":{\"id\":1,\"username\":\"admin\",\"first_name\":\"\",\"last_name\":\"\"},\"modified_by\":{\"id\":1,\"username\":\"admin\",\"first_name\":\"\",\"last_name\":\"\"},\"user_capabilities\":{\"delete\":true,\"start\":true},\"labels\":{\"count\":0,\"results\":[]},\"extra_credentials\":[],\"credentials\":[{\"id\":3,\"name\":\"test_cred\",\"description\":\"\",\"kind\":\"ssh\",\"cloud\":false,\"credential_type_id\":1}]},\"created\":\"2021-01-11T02:30:52.000443Z\",\"modified\":\"2021-01-11T02:30:53.298032Z\",\"name\":\"my-first-template\",\"description\":\"\",\"job_type\":\"run\",\"inventory\":3,\"project\":6,\"playbook\":\"hello.yaml\",\"forks\":0,\"limit\":\"\",\"verbosity\":0,\"extra_vars\":\"{}\",\"job_tags\":\"\",\"force_handlers\":false,\"skip_tags\":\"\",\"start_at_task\":\"\",\"timeout\":0,\"use_fact_cache\":false,\"unified_job_template\":7,\"launch_type\":\"manual\",\"status\":\"pending\",\"failed\":false,\"started\":null,\"finished\":null,\"elapsed\":0.0,\"job_args\":\"\",\"job_cwd\":\"\",\"job_env\":{},\"job_explanation\":\"\",\"execution_node\":\"\",\"controller_node\":\"\",\"result_traceback\":\"\",\"event_processing_finished\":false,\"job_template\":7,\"passwords_needed_to_start\":[],\"ask_diff_mode_on_launch\":false,\"ask_variables_on_launch\":false,\"ask_limit_on_launch\":false,\"ask_tags_on_launch\":false,\"ask_skip_tags_on_launch\":false,\"ask_job_type_on_launch\":false,\"ask_verbosity_on_launch\":false,\"ask_inventory_on_launch\":false,\"ask_credential_on_launch\":false,\"allow_simultaneous\":false,\"artifacts\":{},\"scm_revision\":\"\",\"instance_group\":null,\"diff_mode\":false,\"job_slice_number\":0,\"job_slice_count\":1,\"credential\":3,\"vault_credential\":null}\n";
//
        //创建SSH认证
//        createCredentials(createCredentialsUrl);
        //创建受控主机清单 inventories hosts
//        createInventories(createInventoriesUrl);
//        CreateHosts(createHostsUrl);
        //创建项目 projects
//        createProjects(createProjectsUrl);
        //创建工作模板（指定需要执行的playbook）
//        CreateJobTemplate(createJobTemplateUrl);
        //执行工作模板并获取playbook执行后的完整日志
//        launchJobTemplate(13);

    }

    public static Events getAnsibleJobTemplateLog(LaunchResult launchResult){
        if (launchResult == null) {
            return null;
        }
        Boolean actFlag = false;
        //获取job_templates状态请求地址（Running，failed，Successful）
        String activityStreamUrl = launchResult.getRelated().getActivityStream();
        String activityStreamRequestUrl = "https://ansible"+activityStreamUrl;

        String jobEventsUrl = launchResult.getRelated().getJobEvents();
        String jobEventsRequestUrl = "https://ansible"+jobEventsUrl;

        String status = HttpClientUtil.doGet(activityStreamRequestUrl);
        Activity activity = (Activity) strToObj(status, Activity.class);
        String statu = activity.getResults().get(0).getSummaryfields().getJob().get(0).getStatus();
        if (statu.equals("successful") || statu.equals("failed")){
            actFlag = true;
        }
        while (!actFlag){
            status = HttpClientUtil.doGet(activityStreamRequestUrl);
            activity = (Activity) strToObj(status, Activity.class);
            statu = activity.getResults().get(0).getSummaryfields().getJob().get(0).getStatus();
            if (statu.equals("successful") || statu.equals("failed")){
                actFlag = true;
            }
            System.out.println(statu);
        }
        List<EventLog> nextResults = null;
        String jobEventsStr = HttpClientUtil.doGet(jobEventsRequestUrl);
//        System.out.println("jobEventsStr==>"+jobEventsStr);
        Events events = (Events)strToObj(jobEventsStr, Events.class);
//        System.out.println("events obj===>"+events);
        String next = events.getNext();
        List<EventLog> results = events.getResults();
        if (next != null){
            String nextUrl = "https://ansible"+next;
            String nextEvents = HttpClientUtil.doGet(nextUrl);
            Events nextEventsObj = (Events)strToObj(nextEvents, Events.class);
            System.out.println("nextevents obj ===>"+nextEventsObj);
            nextResults = nextEventsObj.getResults();

            System.out.println(results.size());
            results.addAll(nextResults);
            events.setResults(results);
        }

//        System.out.println("all events===》"+events);
        System.out.println("results.size:::"+results.size());
        return events;
    }

    public static void createCredentials (String url){
        Map<String, Object> paramMap = new HashMap<String,Object>();
        Inputs inputs = new Inputs();
        inputs.setUsername("root");
        inputs.setPassword("linux@441026");
        paramMap.put("credential_type",1);
        paramMap.put("description","");
        paramMap.put("inputs",inputs);
        paramMap.put("name","java_cred_3");
        paramMap.put("organization",null);
        paramMap.put("user",1);
        String result = TestHttp.doPostWithBasicAuth(url,paramMap);
        System.out.println(result);
    }

    public static void createInventories (String url){
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("name","java_inv_1");
        paramMap.put("organization",1);
        paramMap.put("variables","---");
        String result = TestHttp.doPostWithBasicAuth(url, paramMap);
        System.out.println(result);
    }

    public static void CreateHosts (String url){
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("description","java-api");
        paramMap.put("enabled",true);
        paramMap.put("inventory",6);
        paramMap.put("name","java-test-host1");
        paramMap.put("variables","---\r\nansible_host: 10.119.86.174");
        String result = TestHttp.doPostWithBasicAuth(url, paramMap);
        System.out.println(result);
    }

    public static void createProjects (String url){
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("base_dir","/var/lib/awx/projects");
        paramMap.put("custom_virtualenv",null);
        paramMap.put("local_path","java-pro");
        paramMap.put("name","java-pro");
        paramMap.put("organization",1);
        paramMap.put("scm_type","");
        paramMap.put("scm_update_cache_timeout",0);
        String result = TestHttp.doPostWithBasicAuth(url, paramMap);
        System.out.println(result);
    }

    public static void CreateJobTemplate (String url){
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("allow_callbacks",false);
        paramMap.put("ask_credential_on_launch",false);
        paramMap.put("ask_diff_mode_on_launch",false);
        paramMap.put("ask_inventory_on_launch",false);
        paramMap.put("ask_job_type_on_launch",false);
        paramMap.put("ask_limit_on_launch",false);
        paramMap.put("ask_skip_tags_on_launch",false);
        paramMap.put("ask_tags_on_launch",false);
        paramMap.put("ask_variables_on_launch",false);
        paramMap.put("ask_verbosity_on_launch",false);
        paramMap.put("custom_virtualenv",null);
        paramMap.put("description","java-api");
        paramMap.put("extra_vars","");
        paramMap.put("forks",0);
        paramMap.put("inventory",6);
        paramMap.put("job_slice_count",1);
        paramMap.put("job_tags","");
        paramMap.put("job_type","run");
        paramMap.put("name","java-api-template2");
        paramMap.put("playbook","hello.yaml");
        paramMap.put("project",9);
        paramMap.put("skip_tags","");
        paramMap.put("survey_enabled",false);
        paramMap.put("timeout",0);
        paramMap.put("verbosity",0);
        paramMap.put("credential",6);
        String result = TestHttp.doPostWithBasicAuth(url, paramMap);
        System.out.println(result);
    }

    public static void launchJobTemplate(int jobTemplateId) {
        Map<String, Object> paramMap = new HashMap<String,Object>();
        String launchUrl = "https://ansible/api/v2/job_templates/"+jobTemplateId+"/launch/";
        String result = TestHttp.doPostWithBasicAuth(launchUrl, paramMap);
//        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        LaunchResult launchResult = JSON.toJavaObject(jsonObject, LaunchResult.class);
        System.out.println("launchResult obj===>"+launchResult);
        Events ansibleJobTemplateLog = getAnsibleJobTemplateLog(launchResult);
        System.out.println(ansibleJobTemplateLog);
    }


    public static Object strToObj(String str,Class tClass) {
        if (str ==null || str.equals("")){
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(str);
        Object object = JSON.toJavaObject(jsonObject, tClass);
        return object;
    }
}
