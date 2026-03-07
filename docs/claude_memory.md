# 项目记忆文件

这个文件包含Claude在后续会话中需要记住的关于本项目的信息。

## 基本信息

- **项目名称**: Leet-code
- **项目类型**: LeetCode刷题项目（Java实现）
- **项目路径**: `/Users/tiaotiao/Documents/coding/project/Leet-code`
- **创建时间**: 2026-03-04

## 项目结构

```
Leet-code/
├── .gitignore          # Git忽略规则（忽略out/目录等）
├── .idea/              # IntelliJ IDEA配置目录
├── com/                # Java源代码目录
├── docs/               # 文档目录
│   ├── 力扣刷题笔记.md    # 刷题笔记
│   ├── 力扣刷题笔记.assets/ # 笔记资源文件
│   └── claude_memory.md # 本记忆文件
├── out/                # 编译输出目录（已忽略）
└── Leet-code.iml      # IntelliJ项目文件
```

## 重要配置

### Git配置
- `out/` 目录已被添加到 `.gitignore` 中，不会被Git跟踪
- 忽略规则创建于: 2026-03-04

### 远程仓库
- **github**: https://github.com/pumpkins429/Leet-code.git
- **origin (gitee)**: https://gitee.com/pumpkinsBig/Leet-code.git
- 用户偏好：默认推送到 github，不需要推送到 gitee

### Trae CLI 配置
- 安装路径：`/Applications/Trae CN.app/Contents/Resources/app/bin/trae`
- 需要将路径添加到 PATH 才能在终端直接使用 `trae` 命令
- 添加方法：在 `~/.zshrc` 中添加 `export PATH="/Applications/Trae CN.app/Contents/Resources/app/bin:$PATH"`

### 开发环境
- 使用IntelliJ IDEA进行Java开发
- 使用 Trae IDE 作为 AI 辅助编程工具
- 项目包含LeetCode题目解决方案

## 近期操作记录

### 2026-03-04
1. 创建了 `.gitignore` 文件，添加了 `out/` 目录的忽略规则
2. 创建了本记忆文件，用于Claude在后续会话中了解项目背景

### 2026-03-07
1. 添加了新题目 T679_MaxNumOfKSumPairs.java（K 和数对的最大数目）
2. 更新了刷题笔记，添加到双指针分类
3. 配置了 Trae CLI 使用说明

## 用户偏好与要求

- 用户希望Claude在开始新会话时读取此记忆文件
- 项目主要包含LeetCode题目的Java解决方案
- 需要维护好Git忽略规则，避免提交编译输出文件

## 注意事项

1. 每次会话开始时，请提醒用户"正在读取项目记忆文件..."
2. 根据记忆文件中的信息提供有针对性的帮助
3. 如有重要变更，可更新此文件内容

---

*此文件由Claude自动生成，可根据需要修改和补充内容。*