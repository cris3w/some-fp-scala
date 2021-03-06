package com.examples.PureDataAccess.FromScratch.impure

import com.examples.PureDataAccess.commons.Domain._
import com.examples.PureDataAccess.FromScratch.pure._


object DeepIdInterpreter {

  def run[U](program: StoreProgram[U]): U =
    program match {
      case PutUser(user: User)        => IdStore.putUser(user)
      case PutGroup(group: Group)     => IdStore.putGroup(group)
      case GetGroup(gid: Int)         => IdStore.getGroup(gid)
      case GetUser(uid: Int)          => IdStore.getUser(uid)
      case PutJoin(join: JoinRequest) => IdStore.putJoin(join)
      case PutMember(member: Member)  => IdStore.putMember(member)
      case Returns(value)             => IdStore.returns(value)
      case Sequence(program, next)    => IdStore.doAndThen(run(program))(next andThen run)
    }
}
