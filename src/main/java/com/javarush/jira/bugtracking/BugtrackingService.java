package com.javarush.jira.bugtracking;

import com.javarush.jira.bugtracking.to.NodeTo;
import com.javarush.jira.common.BaseMapper;
import com.javarush.jira.common.BaseRepository;
import com.javarush.jira.common.model.TitleEntity;
import com.javarush.jira.common.to.TreeNode;
import com.javarush.jira.common.util.Util;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class BugtrackingService<E extends TitleEntity, T extends NodeTo<T>> {
    private final BaseRepository<E> repository;
    private final BaseMapper<E, T> mapper;

    public List<TreeNode<T>> getTree() {
        List<E> entities = repository.findAll();
        return Util.makeTree(mapper.toToList(entities));
    }
}
