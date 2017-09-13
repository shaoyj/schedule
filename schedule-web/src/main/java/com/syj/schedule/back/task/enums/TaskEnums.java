package com.syj.schedule.back.task.enums;

/**
 * Created by syj on 2016/9/18 0018.
 */
public class TaskEnums {

    public enum Config {
        ROOT("/schedule"),
        ROOT_LOG("/schedule/log"),
        SCHEDULE_PATH("/schedule/task_info");
        private String desc;

        Config(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }


    public enum ObjectType {


        TASK_INFO("task_info配置", "task_info");

        private String desc;

        private String value;

        ObjectType(String desc, String value) {
            this.desc = desc;
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public String getValue() {
            return value;
        }
    }

}
