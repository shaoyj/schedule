# schedule
主要解决分布式环境当中quartz的调度不适合多份部署的影响<br/>
当前项目只支持同一时间保证只有一份job会被调度<br/>

目前项目支持到热部署，实时修改项目的job调度时间等<br/>

修复了项目重启定时任务失效的bug<br/>
<br/>

EXAMPLE:<br/>
pom.xml<br/>
 &lt;dependency&gt;<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&lt;groupId&gt;com.syj.schedule&lt;/groupId&gt;<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&lt;artifactId&gt;schedule-core&lt;/artifactId&gt;<br/>
  &nbsp;&nbsp;&nbsp;&nbsp;&lt;version&gt;1.0-SNAPSHOT&lt;/version&gt;<br/>
        &lt;/dependency&gt;<br/>

spring.xml<br/>

@Configuration<br/>
@ComponentScan("com.syj.schedule")<br/>
public class SpringBeans {<br/>
<br/>
    private String zookeeperlist="localhost:2181";<br/>
<br/>
    @Autowired<br/>
    ZookeeperProfile profile;<br/>
<br/>
    @Bean<br/>
    public ZookeeperProfile createProfile(){<br/>
        ZookeeperProfile profile = new ZookeeperProfile(zookeeperlist,"/schedule");<br/>
 &nbsp;&nbsp; &nbsp;&nbsp;return profile;<br/>
    }<br/>
<br/>
    @Bean<br/>
    public ScheduleSpringFactroy createFactory(){<br/>
 &nbsp;&nbsp; &nbsp;&nbsp;ScheduleSpringFactroy factroy = new ScheduleSpringFactroy();<br/>
 &nbsp;&nbsp; &nbsp;&nbsp;factroy.setZookeeperProfile(profile);<br/>
 &nbsp;&nbsp; &nbsp;&nbsp;return factroy;<br/>
 &nbsp;&nbsp;}<br/>
}<br/>
<br/>

