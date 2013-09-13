package am.projects.ajf.monitor.model;

import java.util.Date;

/**
 * User: mlecoutre
 * Date: 13/09/13
 * Time: 10:18
 */
public class MonitorUnit {

    private String called;
    private String caller;
    private String event_type;
    private String action_type;
    private int duration;
    private String host_name;
    private String app_name;
    private String user_id;
    private String srv_type;
    private String ut_name;
    private String srv_name;
    private String func_name;
    private Date running_time;
    private Integer running_time_m;
    private String uid;
    private int evt_num_seq;
    private String thread_uid;
    private String evt_depth;

    public String getCalled() {
        return called;
    }

    public void setCalled(String called) {
        this.called = called;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSrv_type() {
        return srv_type;
    }

    public void setSrv_type(String srv_type) {
        this.srv_type = srv_type;
    }

    public String getUt_name() {
        return ut_name;
    }

    public void setUt_name(String ut_name) {
        this.ut_name = ut_name;
    }

    public String getSrv_name() {
        return srv_name;
    }

    public void setSrv_name(String srv_name) {
        this.srv_name = srv_name;
    }

    public String getFunc_name() {
        return func_name;
    }

    public void setFunc_name(String func_name) {
        this.func_name = func_name;
    }

    public Date getRunning_time() {
        return running_time;
    }

    public void setRunning_time(Date running_time) {
        this.running_time = running_time;
    }

    public Integer getRunning_time_m() {
        return running_time_m;
    }

    public void setRunning_time_m(Integer running_time_m) {
        this.running_time_m = running_time_m;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getEvt_num_seq() {
        return evt_num_seq;
    }

    public void setEvt_num_seq(int evt_num_seq) {
        this.evt_num_seq = evt_num_seq;
    }

    public String getThread_uid() {
        return thread_uid;
    }

    public void setThread_uid(String thread_uid) {
        this.thread_uid = thread_uid;
    }

    public String getEvt_depth() {
        return evt_depth;
    }

    public void setEvt_depth(String evt_depth) {
        this.evt_depth = evt_depth;
    }
}
