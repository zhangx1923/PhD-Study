## Slurm is the workload manager that is used to process jobs. 
SLURM 是一个可用于大型计算节点集群的高度可伸缩的集群管理器和作业调度系统。SLURM 维护着一个待处理工作的队列并管理此工作的整体资源利用。SLURM 将作业分发给一组已分配的节点来执行

## 架构
![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/Slurmcomponents.gif)
### slurmctld
Slurm 有至少一个集中管理器slurmctld用于监控资源和作业，因为 slurmctld 对于整个集群管理来说至关重要，所以为了保证系统的高可用，你可以选择部署多个备用的slurmctld用以容灾。绝大多数功能和状态都是由 slurmctld 提供和管理的，安装了 slurmctld 的节点我们称之为主控节点，它就像是系统中的指挥官，掌控全局信息并指挥其他程序完成作业。slurmctld 与 slurmd 是一对多的关系

### slurmd
每个计算节点上都会运行 slurmd 进程，用以接收主控节点的指令并汇报节点及任务状态。

### slurmdbd
可选的，没有它 slurmd 的作业系统也可以正常工作，不过实际环境中它却是必不可少的，它用于提供管理账户信息、资源使用情况、作业统计报表等等功能。



![image](https://raw.githubusercontent.com/CPS-zhangX/PhD-Study/master/images/Slurmentities.gif)

### node
每一台安装并正确配置了 slurmd 进程的服务器就是一个节点。安装了 slurmctld 的节点虽然我们称之为主控节点，但实际上它并不是我们这里讨论的节点，这里指的节点即计算资源。
### partition
分区将一组节点组合成逻辑上的一个集合，也有人称之为 “队列”，实际上节点间并没有队列的概念，所以我认为“分区” 是一个更合适的翻译。
### job
**特定时间为用户进行的一次资源申请或分配即可看作一个作业**。这和我们惯性思维中的作业概念并不一致，传统意义上我们总是认为作业应该是某个运行的脚本或者程序，但事实上 Slurm 的作业只代表一次资源申请或分配。理解这个区别将有利于你理解 Slurm 中一些比较高级的用法。
### job step
Slurm 中有作业步的概念，你可以理解为子作业。这允许我们在某个作业中分步骤的细分使用计算资源。


## 基本命令
常用： https://usc-rc.github.io/sys-tutorial/slurm-scheduler

full version: https://slurm.schedmd.com/pdfs/summary.pdf


