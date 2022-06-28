package ru.swarm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Util{
    public static String profileDefault = "# ~/.profile: executed by the command interpreter for login shells.\n" +
            "# This file is not read by bash(1), if ~/.bash_profile or ~/.bash_login\n" +
            "# exists.\n" +
            "# see /usr/share/doc/bash/examples/startup-files for examples.\n" +
            "# the files are located in the bash-doc package.\n" +
            "\n" +
            "# the default umask is set in /etc/profile; for setting the umask\n" +
            "# for ssh logins, install and configure the libpam-umask package.\n" +
            "#umask 022\n" +
            "\n" +
            "# if running bash\n" +
            "if [ -n \"$BASH_VERSION\" ]; then\n" +
            "    # include .bashrc if it exists\n" +
            "    if [ -f \"$HOME/.bashrc\" ]; then\n" +
            "\t. \"$HOME/.bashrc\"\n" +
            "    fi\n" +
            "fi\n" +
            "\n" +
            "# set PATH so it includes user's private bin if it exists\n" +
            "if [ -d \"$HOME/bin\" ] ; then\n" +
            "    PATH=\"$HOME/bin:$PATH\"\n" +
            "fi\n" +
            "\n" +
            "# set PATH so it includes user's private bin if it exists\n" +
            "if [ -d \"$HOME/.local/bin\" ] ; then\n" +
            "    PATH=\"$HOME/.local/bin:$PATH\"\n" +
            "fi\n";
    public static String zshrcDefault="# The following lines were added by compinstall\n" +
            "\n" +
            "zstyle ':completion:*' completer _expand _complete _ignored _approximate\n" +
            "zstyle ':completion:*' group-name ''\n" +
            "zstyle ':completion:*' list-colors ''\n" +
            "zstyle ':completion:*' list-prompt %SAt %p: Hit TAB for more, or the character to insert%s\n" +
            "zstyle ':completion:*' matcher-list '' '' '' 'm:{[:lower:]}={[:upper:]} m:{[:lower:][:upper:]}={[:upper:][:lower:]} r:|[._-]=** r:|=** l:|=*'\n" +
            "zstyle ':completion:*' menu select=5\n" +
            "zstyle ':completion:*' select-prompt %SScrolling active: current selection at %p%s\n" +
            "zstyle ':completion:*' use-compctl true\n" +
            "zstyle ':completion:*' verbose true\n" +
            "zstyle :compinstall filename '/home/swarm/.zshrc'\n" +
            "\n" +
            "autoload -Uz compinit\n" +
            "compinit\n" +
            "# End of lines added by compinstall\n" +
            "# Lines configured by zsh-newuser-install\n" +
            "HISTFILE=~/.histfile\n" +
            "HISTSIZE=1000\n" +
            "SAVEHIST=1000\n" +
            "# End of lines configured by zsh-newuser-install\n";
    public static String bashrcDefault = "# ~/.bashrc: executed by bash(1) for non-login shells.\n" +
            "# see /usr/share/doc/bash/examples/startup-files (in the package bash-doc)\n" +
            "# for examples\n" +
            "\n" +
            "# If not running interactively, don't do anything\n" +
            "case $- in\n" +
            "    *i*) ;;\n" +
            "      *) return;;\n" +
            "esac\n" +
            "\n" +
            "# don't put duplicate lines or lines starting with space in the history.\n" +
            "# See bash(1) for more options\n" +
            "HISTCONTROL=ignoreboth\n" +
            "\n" +
            "# append to the history file, don't overwrite it\n" +
            "shopt -s histappend\n" +
            "\n" +
            "# for setting history length see HISTSIZE and HISTFILESIZE in bash(1)\n" +
            "HISTSIZE=1000\n" +
            "HISTFILESIZE=2000\n" +
            "\n" +
            "# check the window size after each command and, if necessary,\n" +
            "# update the values of LINES and COLUMNS.\n" +
            "shopt -s checkwinsize\n" +
            "\n" +
            "# If set, the pattern \"**\" used in a pathname expansion context will\n" +
            "# match all files and zero or more directories and subdirectories.\n" +
            "#shopt -s globstar\n" +
            "\n" +
            "# make less more friendly for non-text input files, see lesspipe(1)\n" +
            "[ -x /usr/bin/lesspipe ] && eval \"$(SHELL=/bin/sh lesspipe)\"\n" +
            "\n" +
            "# set variable identifying the chroot you work in (used in the prompt below)\n" +
            "if [ -z \"${debian_chroot:-}\" ] && [ -r /etc/debian_chroot ]; then\n" +
            "    debian_chroot=$(cat /etc/debian_chroot)\n" +
            "fi\n" +
            "\n" +
            "# set a fancy prompt (non-color, unless we know we \"want\" color)\n" +
            "case \"$TERM\" in\n" +
            "    xterm-color|*-256color) color_prompt=yes;;\n" +
            "esac\n" +
            "\n" +
            "# uncomment for a colored prompt, if the terminal has the capability; turned\n" +
            "# off by default to not distract the user: the focus in a terminal window\n" +
            "# should be on the output of commands, not on the prompt\n" +
            "#force_color_prompt=yes\n" +
            "\n" +
            "if [ -n \"$force_color_prompt\" ]; then\n" +
            "    if [ -x /usr/bin/tput ] && tput setaf 1 >&/dev/null; then\n" +
            "\t# We have color support; assume it's compliant with Ecma-48\n" +
            "\t# (ISO/IEC-6429). (Lack of such support is extremely rare, and such\n" +
            "\t# a case would tend to support setf rather than setaf.)\n" +
            "\tcolor_prompt=yes\n" +
            "    else\n" +
            "\tcolor_prompt=\n" +
            "    fi\n" +
            "fi\n" +
            "\n" +
            "if [ \"$color_prompt\" = yes ]; then\n" +
            "    PS1='${debian_chroot:+($debian_chroot)}\\[\\033[01;32m\\]\\u@\\h\\[\\033[00m\\]:\\[\\033[01;34m\\]\\w\\[\\033[00m\\]\\$ '\n" +
            "else\n" +
            "    PS1='${debian_chroot:+($debian_chroot)}\\u@\\h:\\w\\$ '\n" +
            "fi\n" +
            "unset color_prompt force_color_prompt\n" +
            "\n" +
            "# If this is an xterm set the title to user@host:dir\n" +
            "case \"$TERM\" in\n" +
            "xterm*|rxvt*)\n" +
            "    PS1=\"\\[\\e]0;${debian_chroot:+($debian_chroot)}\\u@\\h: \\w\\a\\]$PS1\"\n" +
            "    ;;\n" +
            "*)\n" +
            "    ;;\n" +
            "esac\n" +
            "\n" +
            "# enable color support of ls and also add handy aliases\n" +
            "if [ -x /usr/bin/dircolors ]; then\n" +
            "    test -r ~/.dircolors && eval \"$(dircolors -b ~/.dircolors)\" || eval \"$(dircolors -b)\"\n" +
            "    alias ls='ls --color=auto'\n" +
            "    #alias dir='dir --color=auto'\n" +
            "    #alias vdir='vdir --color=auto'\n" +
            "\n" +
            "    alias grep='grep --color=auto'\n" +
            "    alias fgrep='fgrep --color=auto'\n" +
            "    alias egrep='egrep --color=auto'\n" +
            "fi\n" +
            "\n" +
            "# colored GCC warnings and errors\n" +
            "#export GCC_COLORS='error=01;31:warning=01;35:note=01;36:caret=01;32:locus=01:quote=01'\n" +
            "\n" +
            "# some more ls aliases\n" +
            "alias ll='ls -alF'\n" +
            "alias la='ls -A'\n" +
            "alias l='ls -CF'\n" +
            "\n" +
            "# Add an \"alert\" alias for long running commands.  Use like so:\n" +
            "#   sleep 10; alert\n" +
            "alias alert='notify-send --urgency=low -i \"$([ $? = 0 ] && echo terminal || echo error)\" \"$(history|tail -n1|sed -e '\\''s/^\\s*[0-9]\\+\\s*//;s/[;&|]\\s*alert$//'\\'')\"'\n" +
            "\n" +
            "# Alias definitions.\n" +
            "# You may want to put all your additions into a separate file like\n" +
            "# ~/.bash_aliases, instead of adding them here directly.\n" +
            "# See /usr/share/doc/bash-doc/examples in the bash-doc package.\n" +
            "\n" +
            "if [ -f ~/.bash_aliases ]; then\n" +
            "    . ~/.bash_aliases\n" +
            "fi\n" +
            "\n" +
            "# enable programmable completion features (you don't need to enable\n" +
            "# this, if it's already enabled in /etc/bash.bashrc and /etc/profile\n" +
            "# sources /etc/bash.bashrc).\n" +
            "if ! shopt -oq posix; then\n" +
            "  if [ -f /usr/share/bash-completion/bash_completion ]; then\n" +
            "    . /usr/share/bash-completion/bash_completion\n" +
            "  elif [ -f /etc/bash_completion ]; then\n" +
            "    . /etc/bash_completion\n" +
            "  fi\n" +
            "fi\n";
    public static String zshrcDir = ".zshDir";
    public static String  profileDir = "profile";
    public static String  bashrcDir = "bashrc";
    public static String  environmentDir = "/etc/environment";
    public static String  etcProfileDir = "/etc/profile";
    public static String  passwdDir = "/etc/passwd";
    public static String etcEnvDefault = "/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/snap/bin\n";
    public static String etcPassDefault = "root:x:0:0:root:/root:/usr/bin/zsh\n" +
            "daemon:x:1:1:daemon:/usr/sbin:/usr/sbin/nologin\n" +
            "bin:x:2:2:bin:/bin:/usr/sbin/nologin\n" +
            "sys:x:3:3:sys:/dev:/usr/sbin/nologin\n" +
            "sync:x:4:65534:sync:/bin:/bin/sync\n" +
            "games:x:5:60:games:/usr/games:/usr/sbin/nologin\n" +
            "man:x:6:12:man:/var/cache/man:/usr/sbin/nologin\n" +
            "lp:x:7:7:lp:/var/spool/lpd:/usr/sbin/nologin\n" +
            "mail:x:8:8:mail:/var/mail:/usr/sbin/nologin\n" +
            "news:x:9:9:news:/var/spool/news:/usr/sbin/nologin\n" +
            "uucp:x:10:10:uucp:/var/spool/uucp:/usr/sbin/nologin\n" +
            "proxy:x:13:13:proxy:/bin:/usr/sbin/nologin\n" +
            "www-data:x:33:33:www-data:/var/www:/usr/sbin/nologin\n" +
            "backup:x:34:34:backup:/var/backups:/usr/sbin/nologin\n" +
            "list:x:38:38:Mailing List Manager:/var/list:/usr/sbin/nologin\n" +
            "irc:x:39:39:ircd:/var/run/ircd:/usr/sbin/nologin\n" +
            "gnats:x:41:41:Gnats Bug-Reporting System (admin):/var/lib/gnats:/usr/sbin/nologin\n" +
            "nobody:x:65534:65534:nobody:/nonexistent:/usr/sbin/nologin\n" +
            "systemd-network:x:100:102:systemd Network Management,,,:/run/systemd:/usr/sbin/nologin\n" +
            "systemd-resolve:x:101:103:systemd Resolver,,,:/run/systemd:/usr/sbin/nologin\n" +
            "systemd-timesync:x:102:104:systemd Time Synchronization,,,:/run/systemd:/usr/sbin/nologin\n" +
            "messagebus:x:103:106::/nonexistent:/usr/sbin/nologin\n" +
            "syslog:x:104:110::/home/syslog:/usr/sbin/nologin\n" +
            "_apt:x:105:65534::/nonexistent:/usr/sbin/nologin\n" +
            "tss:x:106:111:TPM software stack,,,:/var/lib/tpm:/bin/false\n" +
            "rtkit:x:107:112:RealtimeKit,,,:/proc:/usr/sbin/nologin\n" +
            "systemd-coredump:x:108:113:systemd Core Dumper,,,:/run/systemd:/usr/sbin/nologin\n" +
            "kernoops:x:109:65534:Kernel Oops Tracking Daemon,,,:/:/usr/sbin/nologin\n" +
            "uuidd:x:110:118::/run/uuidd:/usr/sbin/nologin\n" +
            "cups-pk-helper:x:111:114:user for cups-pk-helper service,,,:/home/cups-pk-helper:/usr/sbin/nologin\n" +
            "tcpdump:x:112:120::/nonexistent:/usr/sbin/nologin\n" +
            "geoclue:x:113:122::/var/lib/geoclue:/usr/sbin/nologin\n" +
            "avahi-autoipd:x:114:123:Avahi autoip daemon,,,:/var/lib/avahi-autoipd:/usr/sbin/nologin\n" +
            "usbmux:x:115:46:usbmux daemon,,,:/var/lib/usbmux:/usr/sbin/nologin\n" +
            "dnsmasq:x:116:65534:dnsmasq,,,:/var/lib/misc:/usr/sbin/nologin\n" +
            "_flatpak:x:117:125:Flatpak system-wide installation helper,,,:/nonexistent:/usr/sbin/nologin\n" +
            "avahi:x:118:126:Avahi mDNS daemon,,,:/var/run/avahi-daemon:/usr/sbin/nologin\n" +
            "saned:x:119:127::/var/lib/saned:/usr/sbin/nologin\n" +
            "lightdm:x:120:128:Light Display Manager:/var/lib/lightdm:/bin/false\n" +
            "colord:x:121:130:colord colour management daemon,,,:/var/lib/colord:/usr/sbin/nologin\n" +
            "pulse:x:122:131:PulseAudio daemon,,,:/var/run/pulse:/usr/sbin/nologin\n" +
            "speech-dispatcher:x:123:29:Speech Dispatcher,,,:/run/speech-dispatcher:/bin/false\n" +
            "nm-openvpn:x:124:133:NetworkManager OpenVPN,,,:/var/lib/openvpn/chroot:/usr/sbin/nologin\n" +
            "hplip:x:125:7:HPLIP system user,,,:/run/hplip:/bin/false\n" +
            "swarm:x:1000:1000:swarm,,,:/home/swarm:/usr/bin/zsh\n" +
            "sshd:x:127:65534::/run/sshd:/usr/sbin/nologin\n" +
            "ftp:x:128:138:ftp daemon,,,:/srv/ftp:/usr/sbin/nologin\n" +
            "festival:x:129:29::/nonexistent:/usr/sbin/nologin\n" +
            "puppet:x:130:139:Puppet configuration management daemon,,,:/var/lib/puppet:/usr/sbin/nologin\n" +
            "debian-tor:x:131:140::/var/lib/tor:/bin/false\n" +
            "sddm:x:132:141:Simple Desktop Display Manager:/var/lib/sddm:/bin/false\n" +
            "nvidia-persistenced:x:126:135:NVIDIA Persistence Daemon,,,:/nonexistent:/sbin/nologin\n";
    public static String etcProfDefault = "# /etc/profile: system-wide .profile file for the Bourne shell (sh(1))\n" +
            "# and Bourne compatible shells (bash(1), ksh(1), ash(1), ...).\n" +
            "\n" +
            "if [ \"${PS1-}\" ]; then\n" +
            "  if [ \"${BASH-}\" ] && [ \"$BASH\" != \"/bin/sh\" ]; then\n" +
            "    # The file bash.bashrc already sets the default PS1.\n" +
            "    # PS1='\\h:\\w\\$ '\n" +
            "    if [ -f /etc/bash.bashrc ]; then\n" +
            "      . /etc/bash.bashrc\n" +
            "    fi\n" +
            "  else\n" +
            "    if [ \"`id -u`\" -eq 0 ]; then\n" +
            "      PS1='# '\n" +
            "    else\n" +
            "      PS1='$ '\n" +
            "    fi\n" +
            "  fi\n" +
            "fi\n" +
            "\n" +
            "if [ -d /etc/profile.d ]; then\n" +
            "  for i in /etc/profile.d/*.sh; do\n" +
            "    if [ -r $i ]; then\n" +
            "      . $i\n" +
            "    fi\n" +
            "  done\n" +
            "  unset i\n" +
            "fi\n";

    public static String[] getProperties() throws IOException {
        String path = System.getenv("PATH");
        String[] out =  path.split(":");
        return out;
    }
    public <T> T[] toArray(List<T> list) {
        T array[] = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    public <T> ArrayList<T> toArrayList(T[] array) {
        ArrayList<T> arrayList = new ArrayList<T>();
        arrayList.addAll(List.of(array));
        return arrayList;
    }
    public static String makePath(List<String> properties) {
        String result = "";
        for(String property : properties) {
            result+=property+":";
        }
        return result.substring(0, result.length() - 1);
    }
}
