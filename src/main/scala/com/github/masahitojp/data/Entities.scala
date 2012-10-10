package com.github.masahitojp.data

/**
 * Created with IntelliJ IDEA.
 * User: test
 * Date: 12/10/05
 * Time: 0:33
 */
case class Member()
case class BeatlesMemberNoId(firstName: String, lastName: String) extends Member
case class BeatlesMember(id: Long, firstName: String, lastName: String) extends Member
